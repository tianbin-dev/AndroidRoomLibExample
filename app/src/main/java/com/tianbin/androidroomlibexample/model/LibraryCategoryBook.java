package com.tianbin.androidroomlibexample.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * LibraryCategoryBook
 * Created by tianbin on 2017/8/17.
 */
public class LibraryCategoryBook {

    @Embedded
    public Library library;

    @Relation(parentColumn = "id", entityColumn = "library_id", entity = Category.class)
    public List<CategoryBook> categoryList;

    public static class CategoryBook {
        @Embedded
        public Category category;

        @Relation(parentColumn = "id", entityColumn = "category_id")
        public List<Book> bookList;
    }
}
