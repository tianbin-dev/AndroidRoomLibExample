package com.tianbin.androidroomlibexample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.tianbin.androidroomlibexample.dao.BookDao;
import com.tianbin.androidroomlibexample.dao.CategoryDao;
import com.tianbin.androidroomlibexample.dao.LibraryDao;
import com.tianbin.androidroomlibexample.database.LibraryDatabase;
import com.tianbin.androidroomlibexample.model.Address;
import com.tianbin.androidroomlibexample.model.Book;
import com.tianbin.androidroomlibexample.model.Category;
import com.tianbin.androidroomlibexample.model.Library;
import com.tianbin.androidroomlibexample.model.LibraryAddressName;

import org.hamcrest.collection.IsEmptyIterable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Predicate;

import static com.tianbin.androidroomlibexample.LiveDataTestUtil.getValue;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * LibraryEntityTest
 * Created by tianbin on 2017/8/10.
 */
@RunWith(AndroidJUnit4.class)
public class LibraryDaoTest {

    private LibraryDatabase mDb;

    private LibraryDao mLibraryDao;
    private CategoryDao mCategoryDao;
    private BookDao mBookDao;

    @Before
    public void createDb() {
        //Context context = InstrumentationRegistry.getTargetContext();
        //mDb = Room.inMemoryDatabaseBuilder(context, LibraryDatabase.class).build();
        mDb = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                LibraryDatabase.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();

        mLibraryDao = mDb.libraryDao();
        mCategoryDao = mDb.categoryDao();
        mBookDao = mDb.bookDao();
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

    @Test
    public void queryLibraryByName() throws Exception {

        final Library library = createLibrary();
        mLibraryDao.insert(library);

        final Library query = mLibraryDao.query(library.name);
        assertThat(query.name, equalTo(library.name));
    }

    @Test
    public void queryLibraryAddressName() throws Exception {

        final Library library = createLibrary();
        mLibraryDao.insert(library);

        final List<LibraryAddressName> libraryAddressNames = mLibraryDao.queryLibraryAddressName();
        assertThat(libraryAddressNames.get(0).city, equalTo(library.address.city));
    }

    @Test
    public void queryLibrary() throws Exception {

        final List<Library> libraries = createLibraries();
        mLibraryDao.batchInsert(libraries);

        final List<String> cities = new ArrayList<>();
        cities.add("city 0");
        cities.add("city 1");

        final List<Library> libraryList = mLibraryDao.queryByCityName(cities);
        for (Library library : libraryList) {
            assertTrue(cities.contains(library.address.city));
        }
    }

    private List<Library> createLibraries() {
        final ArrayList<Library> libraries = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            final Library library = new Library();
            library.name = "library " + i;

            final Address address = new Address();
            address.city = "city " + i % 3;
            address.street = "street " + i;
            address.postCode = 11111 * i;

            library.address = address;

            libraries.add(library);
        }
        return libraries;
    }

    @Test
    public void queryLibraryReturnLiveData() throws Exception {

        final Library library = createLibrary();
        mLibraryDao.insert(library);

        final LiveData<List<Library>> listLiveData = mLibraryDao.queryReturnLiveData();
        assertThat(getValue(listLiveData).size(), is(1));
    }

    @Test
    public void queryLibraryReturnFlowable() throws Exception {

        final Library library = createLibrary();
        mLibraryDao.insert(library);

        mLibraryDao.queryReturnFlowable()
                .test()
                // https://github.com/ReactiveX/RxJava/issues/5385
                .awaitDone(5, TimeUnit.SECONDS)
                .assertValue(new Predicate<List<Library>>() {
                    @Override
                    public boolean test(List<Library> libraryList) throws Exception {
                        // The emitted user is the expected one
                        return libraryList.get(0).name.equals(library.name);
                    }
                });
    }

    @Test
    public void queryLibraryByBookName() throws Exception {

        final Library library = initData();

        final Library query = mLibraryDao.findLibraryByBookName("book");
        assertThat(query.name, equalTo(library.name));
    }

    private Library initData() {
        final Library library = new Library();
        library.name = "library 1";
        final long[] libraryIdArr = mLibraryDao.insert(library);

        final Category category = new Category();
        category.name = "category 1";
        category.libraryId = (int) libraryIdArr[0];
        final long[] categoryIdArr = mCategoryDao.insert(category);

        final Book book = new Book();
        book.name = "book";
        book.categoryId = (int) categoryIdArr[0];
        mBookDao.insert(book);

        return library;
    }
}
