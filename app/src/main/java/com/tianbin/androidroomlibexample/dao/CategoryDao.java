package com.tianbin.androidroomlibexample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.tianbin.androidroomlibexample.model.Category;

/**
 * CategoryDao
 * Created by tianbin on 2017/8/9.
 */
@Dao
public interface CategoryDao {

    @Insert
    long[] insert(Category... categories);
}
