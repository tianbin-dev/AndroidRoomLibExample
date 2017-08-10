package com.tianbin.androidroomlibexample.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * DatabaseManager
 * Created by tianbin on 2017/8/10.
 */
public class DatabaseManager {

    public static LibraryDatabase mInstance;

    public static LibraryDatabase initInstance(Context context) {
        if (mInstance == null) {
            synchronized (LibraryDatabase.class) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context,
                            LibraryDatabase.class, "library").build();
                }
            }
        }
        return mInstance;
    }
}
