package com.example.bradperkins.project3.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import com.example.bradperkins.project3.Utilities.FileHelper;
import com.example.bradperkins.project3.Utilities.Person;
import com.example.bradperkins.project3.ViewActivity;

/**
 * Created by bradperkins on 1/20/16.
 */
public class WidgetProvider extends AppWidgetProvider {

    public static final String ACTION_VIEW_DETAILS = "com.example.bradperkins.ACTION_VIEW_DETAILS";
    public static final String EXTRA_ITEM = "com.example.bradperkins.WidgetProvider.EXTRA_ITEM";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int i=0; i< appWidgetIds.length; i++){

            FileHelper.updateWidget(context, appWidgetManager, appWidgetIds[i]);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        RemoteViews widgetViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//        AppWidgetManager manager = AppWidgetManager.getInstance(context);
//        ComponentName componentName = new ComponentName(context, WidgetProvider.class);
//        int[] widgetIDs = manager.getAppWidgetIds(componentName);
//        manager.notifyAppWidgetViewDataChanged(widgetIDs, R.id.person_list);
//        manager.updateAppWidget(widgetIDs, widgetViews);
        super.onReceive(context, intent);

        if (intent.getAction().equals(ACTION_VIEW_DETAILS)) {
            Person person = (Person) intent.getSerializableExtra(EXTRA_ITEM);
            if (person != null) {
                Intent details = new Intent(context, ViewActivity.class);
                details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                details.putExtra(ViewActivity.EXTRA_ITEM, person);
                context.startActivity(details);
            }


        }

    }
}
