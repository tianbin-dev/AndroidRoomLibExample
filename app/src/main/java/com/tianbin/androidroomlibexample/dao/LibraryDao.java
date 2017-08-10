package com.tianbin.androidroomlibexample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.tianbin.androidroomlibexample.model.Library;

import java.util.List;

/**
 * LibraryDao
 * Created by tianbin on 2017/8/9.
 */
@Dao
public interface LibraryDao {

    @Insert
    long[] insert(Library... libraries);

    @Update
    int update(Library... libraries);

    @Delete
    int delete(Library... libraries);

    @Query("SELECT * FROM library")
    List<Library> query();
}
