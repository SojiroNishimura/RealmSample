package com.example.realmsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.realmsample.data.Book;
import com.example.realmsample.data.BookShelf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Permission;
import java.security.Permissions;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.button_create)
    Button mButtonCreate;

    @InjectView(R.id.button_delete)
    Button mButtonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mButtonCreate.setOnClickListener(v -> {
            Log.d("AAA", "Created");
            createBookShelf();
        });

        mButtonDelete.setOnClickListener(v -> {
            Log.d("AAA", "Deleted");
            deleteBookShelf();
        });
    }

    private void createBookShelf() {
        Realm r = Realm.getDefaultInstance();

        // トランザクション開始
        r.beginTransaction();

        Book b = r.createObject(Book.class);
        b.setTitle("Extreme Programming");
        b.setAuthor("Kent Beck");
        b.setPages(181);

        Book b2 = r.createObject(Book.class);
        b2.setTitle("アジャイルサムライ");
        b2.setAuthor("Jonathan Rasmusson");
        b2.setPages(316);

        RealmList<Book> bookList = new RealmList<>();
        bookList.add(b);
        bookList.add(b2);

        BookShelf shelf = r.createObject(BookShelf.class);
        shelf.setBooks(bookList);

        r.commitTransaction();
        // トランザクション終了

        r.close();
    }

    private void deleteBookShelf() {
        Realm r = Realm.getDefaultInstance();

        // 非同期処理開始
        r.executeTransactionAsync(realm -> {
            realm.deleteAll();
            // 非同期の場合は自動的にトランザクション処理が行われる
        });
        r.close();
    }
}
