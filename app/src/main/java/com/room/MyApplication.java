package com.room;

import android.app.Application;

import com.room.model.MyDatabase;

public class MyApplication extends Application
{

    @Override
    public void onCreate() {
        super.onCreate();
        MyDatabase.initDatabase(this);
    }

}


