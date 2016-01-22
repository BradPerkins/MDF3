package com.example.bradperkins.project3.Utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bradperkins.project3.R;

import java.util.ArrayList;

/**
 * Created by bradperkins on 1/20/16.
 */
public class Adapter extends BaseAdapter {

    private static final int ID_CONSTANT = 0x01010101;
    private Context mContext;
    private ArrayList<Person> personList;

    public Adapter(Context _context, ArrayList<Person> _personList){
        mContext = _context;
        personList = _personList;
    }

    @Override
    public int getCount() {

        if (personList == null){
            return 0;
        }
        return personList.size();
    }

    @Override
    public Person getItem(int position) {
        if (personList != null && position < personList.size() && position >= 0){
            return personList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        }

        Person person = getItem(position);

        if (person != null){

            TextView tv =(TextView)convertView.findViewById(R.id.name);
            tv.setText(person.getName());


        }

        return convertView;
    }

}
