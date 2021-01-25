package com.example.stud1;

import android.app.Application;
import android.content.Context;
//class where created and initialized Data Base
public class Stud1App extends Application {

    private static Stud1DatabaseHelper mDBHelper;
//Create Data base
    @Override
    public void onCreate() {
        super.onCreate();
        initDatabase(this);
    }
//initialized Data Base
    private void initDatabase(Context context){
        mDBHelper = new Stud1DatabaseHelper(context);
        // todo call method of dbHelper for create database
    }
//Call  method dbHelper
    public static Stud1DatabaseHelper getDBHelper() {
        return mDBHelper;
    }
}
