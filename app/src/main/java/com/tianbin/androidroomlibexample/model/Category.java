package com.tianbin.androidroomlibexample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Category
 * Created by tianbin on 2017/8/9.
 */
@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "category_name")
    public String name;

    //public List<Book> mBooks;
}
