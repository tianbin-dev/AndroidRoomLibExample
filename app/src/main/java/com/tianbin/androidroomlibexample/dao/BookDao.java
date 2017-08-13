package com.tianbin.androidroomlibexample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.tianbin.androidroomlibexample.model.Book;

/**
 * BookDao
 * Created by tianbin on 2017/8/9.
 */
@Dao
public interface BookDao {

    @Insert
    long[] insert(Book... books);
}
