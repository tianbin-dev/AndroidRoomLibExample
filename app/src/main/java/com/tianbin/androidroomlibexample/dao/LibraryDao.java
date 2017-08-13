package com.tianbin.androidroomlibexample.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.tianbin.androidroomlibexample.model.Library;
import com.tianbin.androidroomlibexample.model.LibraryAddressName;

import java.util.List;

import io.reactivex.Flowable;

/**
 * LibraryDao
 * Created by tianbin on 2017/8/9.
 */
@Dao
public interface LibraryDao {

    @Insert
    long[] insert(Library... libraries);

    @Insert
    long[] batchInsert(List<Library> libraryList);

    @Update
    int update(Library... libraries);

    @Delete
    int delete(Library... libraries);

    @Query("SELECT * FROM library")
    List<Library> query();

    @Query("SELECT * FROM library WHERE library_name = :name")
    Library query(String name);

    @Query("SELECT library_name,city FROM library")
    List<LibraryAddressName> queryLibraryAddressName();

    @Query("SELECT * FROM library WHERE city IN (:cityList)")
    List<Library> queryByCityName(List<String> cityList);

    @Query("SELECT * FROM library")
    LiveData<List<Library>> queryReturnLiveData();

    @Query("SELECT * FROM library")
    Flowable<List<Library>> queryReturnFlowable();

    @Query("SELECT * FROM library")
    Cursor queryReturnCursor();

    @Query("SELECT * FROM library "
            + "INNER JOIN category ON category.library_id = library.id "
            + "INNER JOIN book ON book.category_id = category.id "
            + "WHERE book.name LIKE :bookName")
    Library findLibraryByBookName(String bookName);
}
