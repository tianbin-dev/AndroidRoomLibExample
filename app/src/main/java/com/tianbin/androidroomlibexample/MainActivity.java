package com.tianbin.androidroomlibexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tianbin.androidroomlibexample.database.LibraryDatabase;
import com.tianbin.androidroomlibexample.model.Library;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            @Override
            public void run() {
                super.run();

                final Library library = new Library();
                library.name = "library 1";
                library.address = "address 1";
                LibraryDatabase.getInstance(MainActivity.this).libraryDao().insert(library);
            }
        }.start();
    }
}
