package com.tianbin.androidroomlibexample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tianbin.androidroomlibexample.model.Category;

import java.util.List;

/**
 * CategoryDao
 * Created by tianbin on 2017/8/9.
 */
@Dao
public interface CategoryDao {

    @Insert
    long[] insert(Category... categories);

    @Query("SELECT * FROM category")
    List<Category> query();

    @Delete
    int delete(Category... categories);
}
