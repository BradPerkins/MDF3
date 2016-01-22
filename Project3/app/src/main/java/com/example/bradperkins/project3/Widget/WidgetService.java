package com.example.bradperkins.project3.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by bradperkins on 1/20/16.
 */
public class WidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetViewFactory(getApplicationContext());
    }
}
