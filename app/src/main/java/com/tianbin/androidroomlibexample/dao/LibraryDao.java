package com.tianbin.androidroomlibexample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.tianbin.androidroomlibexample.model.Library;

/**
 * LibraryDao
 * Created by tianbin on 2017/8/9.
 */
@Dao
public interface LibraryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Library library);
}
