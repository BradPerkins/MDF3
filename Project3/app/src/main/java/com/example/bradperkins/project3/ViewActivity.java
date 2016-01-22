package com.example.bradperkins.project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bradperkins.project3.Utilities.Person;
/**
 * Created by bradperkins on 1/20/16.
 */
public class ViewActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "com.example.bradperkins.EXTRA_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();

        TextView nameTV = (TextView)findViewById(R.id.name_tv);
        TextView ageTV = (TextView)findViewById(R.id.age_tv);
        TextView stateTV = (TextView)findViewById(R.id.state_tv);

        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String state = intent.getStringExtra("state");

        nameTV.setText(name);
        ageTV.setText(age);
        stateTV.setText(state);

    }

    public void mainBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
