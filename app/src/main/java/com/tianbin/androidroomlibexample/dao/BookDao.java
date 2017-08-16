package com.tianbin.androidroomlibexample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tianbin.androidroomlibexample.model.Book;

import java.util.List;

/**
 * BookDao
 * Created by tianbin on 2017/8/9.
 */
@Dao
public interface BookDao {

    @Insert
    long[] insert(Book... books);

    @Query("SELECT * FROM book")
    List<Book> query();

    @Delete
    int delete(Book... books);
}
