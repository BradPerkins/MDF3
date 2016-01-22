package com.example.bradperkins.project3;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bradperkins.project3.Utilities.FileHelper;
import com.example.bradperkins.project3.Utilities.Person;
import com.example.bradperkins.project3.Widget.WidgetProvider;

import java.util.ArrayList;
/**
 * Created by bradperkins on 1/20/16.
 */
public class AddActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText ageET;
    private EditText stateET;
    private String action;

    int mWidgetId;

    Person person;

    ArrayList<Person> personList;

    FileHelper fileHelper = new FileHelper();

    Button saveBtn;
    MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameET = (EditText) findViewById(R.id.person_name);
        ageET = (EditText) findViewById(R.id.person_age);
        stateET = (EditText) findViewById(R.id.person_state);

        personList = fileHelper.readData(this);

    }


    public void saveData(View view) {

        String nameText = nameET.getText().toString().trim();
        String ageText = ageET.getText().toString().trim();
        String stateText = stateET.getText().toString().trim();

        if (nameText.isEmpty() || ageText.isEmpty() || stateText.isEmpty()) {
            Toast.makeText(AddActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
        }
        else {

            person = new Person(nameText, ageText, stateText);
            personList.add(person);

            fileHelper.writeData(personList, this);

            nameET.setText("");
            ageET.setText("");
            stateET.setText("");

            Toast.makeText(AddActivity.this, "New Person Added", Toast.LENGTH_SHORT).show();

            Intent widgetIDIntent = getIntent();
            mWidgetId = widgetIDIntent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

            AppWidgetManager manager = AppWidgetManager.getInstance(this);
            FileHelper.updateWidget(this, manager, mWidgetId);

            Intent widgetIntent = new Intent();
            widgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mWidgetId);
            setResult(RESULT_OK, widgetIntent);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }
        finish();
    }

}
