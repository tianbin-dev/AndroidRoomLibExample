package com.tianbin.androidroomlibexample;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.tianbin.androidroomlibexample.dao.LibraryDao;
import com.tianbin.androidroomlibexample.database.LibraryDatabase;
import com.tianbin.androidroomlibexample.model.Address;
import com.tianbin.androidroomlibexample.model.Library;

import org.hamcrest.collection.IsEmptyIterable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * LibraryEntityTest
 * Created by tianbin on 2017/8/10.
 */
@RunWith(AndroidJUnit4.class)
public class LibraryDaoTest {

    private LibraryDao mLibraryDao;
    private LibraryDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, LibraryDatabase.class).build();
        mLibraryDao = mDb.libraryDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void insertLibrary() throws Exception {

        final Library library = createLibrary();
        mLibraryDao.insert(library);
        final List<Library> libraries = mLibraryDao.query();
        assertThat(libraries.get(0).name, equalTo(library.name));
    }

    @NonNull
    private Library createLibrary() throws Exception {

        final Library library = new Library();
        library.name = "library 2";
        final Address address = new Address();
        address.city = "beijing 1";
        address.street = "shang di dong lu";
        address.postCode = 11111;
        library.address = address;
        return library;
    }

    @Test
    public void updateLibrary() throws Exception {

        final Library library = createLibrary();
        mLibraryDao.insert(library);

        final List<Library> libraries = mLibraryDao.query();
        libraries.get(0).name = "library 1";
        mLibraryDao.update(libraries.get(0));

        final List<Library> newLibraries = mLibraryDao.query();
        assertThat(newLibraries.get(0).name, equalTo("library 1"));
    }

    @Test
    public void deleteLibrary() throws Exception {

        final Library library = createLibrary();
        mLibraryDao.insert(library);

        final List<Library> libraries = mLibraryDao.query();
        mLibraryDao.delete(libraries.get(0));

        final List<Library> query = mLibraryDao.query();
        assertThat(query, IsEmptyIterable.<Library>emptyIterable());
    }


}
