package com.tianbin.androidroomlibexample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Category
 * Created by tianbin on 2017/8/9.
 */
@Entity(foreignKeys = @ForeignKey(entity = Library.class, parentColumns = "id", childColumns = "library_id", onDelete = CASCADE),
        indices = {@Index(value = "library_id", unique = true)})
public class Category {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "category_name")
    public String name;
    @ColumnInfo(name = "library_id")
    public int libraryId;

    @Ignore
    public List<Book> mBooks;

}
