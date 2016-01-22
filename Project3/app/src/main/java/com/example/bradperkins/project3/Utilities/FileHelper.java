package com.example.bradperkins.project3.Utilities;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.bradperkins.project3.AddActivity;
import com.example.bradperkins.project3.MainActivity;
import com.example.bradperkins.project3.R;
import com.example.bradperkins.project3.ViewActivity;
import com.example.bradperkins.project3.Widget.WidgetProvider;
import com.example.bradperkins.project3.Widget.WidgetService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by bradperkins on 1/20/16.
 */
public class FileHelper {

    private static final String FILENAME = "personinfo.dat";

    static Person person = new Person();

    public static void writeData(ArrayList<Person> personList, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(personList);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Person> readData(Context context) {
        ArrayList<Person> personList = null;
        try {
            FileInputStream is = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(is);

            personList = (ArrayList<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //In case there is nothing in personlist, catch it
            personList = new ArrayList<>();
            e.printStackTrace();
        }

        return personList;
    }



    public static void updateWidget(Context context, AppWidgetManager manager, int widgetId) {

        Intent serviceIntent = new Intent(context, WidgetService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

        RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        widgetView.setRemoteAdapter(R.id.person_list, serviceIntent);
        widgetView.setEmptyView(R.id.person_list, R.id.empty);

        //intent for list
        Intent activityIntent = new Intent(context, ViewActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setPendingIntentTemplate(R.id.person_list, pIntent);

        //Add Button Intent
        Intent addButtonIntent = new Intent(context, AddActivity.class);
        PendingIntent addPIntent = PendingIntent.getActivity(context, 0, addButtonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        widgetView.setOnClickPendingIntent(R.id.widget_add_btn, addPIntent);

        manager.updateAppWidget(widgetId, widgetView);

    }


}
