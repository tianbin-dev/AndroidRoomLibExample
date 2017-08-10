package com.tianbin.androidroomlibexample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Library
 * Created by tianbin on 2017/8/9.
 */
@Entity
public class Library {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "library_name")
    public String name;

    @Embedded
    public Address address;

    @Ignore
    public List<Category> mCategories;
}
