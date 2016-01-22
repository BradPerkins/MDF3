package com.example.bradperkins.project3;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.bradperkins.project3.Utilities.Adapter;
import com.example.bradperkins.project3.Utilities.FileHelper;
import com.example.bradperkins.project3.Utilities.Person;
import com.example.bradperkins.project3.Widget.WidgetProvider;

import java.util.ArrayList;
/**
 * Created by bradperkins on 1/20/16.
 */
public class MainActivity extends AppCompatActivity {

    FileHelper fileHelper = new FileHelper();

    ListView listView;
    Button addBtn;

    int mWidgetId;

    public ArrayList<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        FileHelper.updateWidget(this, manager, mWidgetId);

        listView = (ListView)findViewById(R.id.listView);
        addBtn = (Button)findViewById(R.id.addBtn);

        personList = new ArrayList<>();

        personList = fileHelper.readData(this);

        fileHelper.readData(this);
        final Adapter adapter = new Adapter(this, personList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(listener);

        deletePerson(adapter, this);

    }

    //Adapter View OnClicklistener
    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Person persons = (Person)adapterView.getAdapter().getItem(position);

            Person removeItemPos = (Person)adapterView.getAdapter().getItem(position);
            Person itemPos = personList.get(position);

            //Pulls Data and sets them to Strings to fill the Text Views
            String personName = persons.getName();
            String personAge = persons.getAge();
            String personState = persons.getState();

            Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
            intent.putExtra("name", personName);
            intent.putExtra("age", personAge);
            intent.putExtra("state", personState);

            intent.putExtra("itemPos", position);

            intent.putStringArrayListExtra("arrayList", itemPos);

            startActivity(intent);

        }
    };


    private void deletePerson(final Adapter adapter, final Context _context) {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                personList.remove(position);
                adapter.notifyDataSetChanged();

                //update personList
                fileHelper.writeData(personList, _context);

                return false;
            }
        });
    }

    public void addBtn(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }


}
