package com.tianbin.androidroomlibexample.model;

import android.arch.persistence.room.ColumnInfo;

/**
 * Address
 * Created by tianbin on 2017/8/10.
 */
public class Address {

    public String city;
    public String street;

    @ColumnInfo(name = "post_code")
    public int postCode;
}
