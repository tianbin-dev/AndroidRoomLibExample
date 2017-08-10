package com.tianbin.androidroomlibexample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tianbin.androidroomlibexample.dao.BookDao;
import com.tianbin.androidroomlibexample.dao.CategoryDao;
import com.tianbin.androidroomlibexample.dao.LibraryDao;
import com.tianbin.androidroomlibexample.model.Book;
import com.tianbin.androidroomlibexample.model.Category;
import com.tianbin.androidroomlibexample.model.Library;

/**
 * LibraryDatabase
 * Created by tianbin on 2017/8/9.
 */
@Database(entities = {Library.class, Category.class, Book.class}, version = 1)
public abstract class LibraryDatabase extends RoomDatabase {

    private static LibraryDatabase mInstance;

    public abstract LibraryDao libraryDao();

    public abstract CategoryDao categoryDao();

    public abstract BookDao bookDao();

    public static LibraryDatabase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LibraryDatabase.class) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context,
                            LibraryDatabase.class, "library.db").build();
                }
            }
        }
        return mInstance;
    }
}
