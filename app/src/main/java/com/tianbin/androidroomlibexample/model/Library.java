package com.tianbin.androidroomlibexample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Library
 * Created by tianbin on 2017/8/9.
 */
@Entity
public class Library {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "library_name")
    public String name;
    public String address;

    //public List<Category> mCategories;
}
