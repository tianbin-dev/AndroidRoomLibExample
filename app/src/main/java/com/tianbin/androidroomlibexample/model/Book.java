package com.tianbin.androidroomlibexample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Book
 * Created by tianbin on 2017/8/9.
 */
@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "book_name")
    public String name;
    public String author;
}
