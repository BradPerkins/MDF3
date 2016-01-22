package com.example.bradperkins.project3.Widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.bradperkins.project3.MainActivity;
import com.example.bradperkins.project3.R;
import com.example.bradperkins.project3.Utilities.FileHelper;
import com.example.bradperkins.project3.Utilities.Person;
import com.example.bradperkins.project3.ViewActivity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bradperkins on 1/20/16.
 */
public class WidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final int ID_CONSTANT = 0x0101010;

    FileHelper fileHelper = new FileHelper();
    //MainActivity mainActivity = new MainActivity();

    private ArrayList<Person> personList;
    private Context mContext;

    public WidgetViewFactory(Context _context) {
        mContext = _context;
        personList = new ArrayList<Person>();

    }

    @Override
    public void onCreate() {
        //Grabs data from file
        personList = fileHelper.readData(mContext);
    }

    @Override
    public void onDataSetChanged() {
        //Grabs data from file
        personList = fileHelper.readData(mContext);

    }

    @Override
    public void onDestroy() {
        personList.clear();
        personList = null;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Person person = personList.get(position);

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.person_item);
        rv.setTextViewText(R.id.name, person.getName());
        rv.setTextViewText(R.id.age, person.getAge());
        rv.setTextViewText(R.id.state, person.getState());

        String personName = person.getName();
        String personAge = person.getAge();
        String personState = person.getState();

        Intent intent = new Intent();
        intent.putExtra("name", personName);
        intent.putExtra("age", personAge);
        intent.putExtra("state", personState);


        intent.putExtra(ViewActivity.EXTRA_ITEM, person);
        rv.setOnClickFillInIntent(R.id.person_item, intent);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
