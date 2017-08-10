package com.tianbin.androidroomlibexample;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * RoomApplication
 * Created by tianbin on 2017/8/9.
 */
public class RoomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }
}
