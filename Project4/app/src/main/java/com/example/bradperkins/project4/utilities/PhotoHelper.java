package com.example.bradperkins.project4.utilities;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Created by bradperkins on 1/25/15.
 */
public class PhotoHelper {



    public static final String FILENAME = "project4data.dat";

    static Photo photo = new Photo();

    public static void writeData(ArrayList<Photo> photoList, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(photoList);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Photo> readData(Context context){
        ArrayList<Photo> photoList = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            photoList = (ArrayList<Photo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {

            photoList = new ArrayList<>();
            e.printStackTrace();
        }
        return photoList;
    }


}
























