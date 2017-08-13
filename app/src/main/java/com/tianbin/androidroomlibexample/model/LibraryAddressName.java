package com.tianbin.androidroomlibexample.model;

import android.arch.persistence.room.ColumnInfo;

/**
 * LibraryAddressName
 * Created by tianbin on 2017/8/12.
 */
public class LibraryAddressName {

    @ColumnInfo(name = "library_name")
    public String libraryName;
    @ColumnInfo(name = "city")
    public String city;
}
