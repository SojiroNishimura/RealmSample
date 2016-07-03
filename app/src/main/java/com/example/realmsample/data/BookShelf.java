package com.example.realmsample.data;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by snishimura on 16/07/03.
 */
public class BookShelf extends RealmObject {
    private static final String TAG = BookShelf.class.getSimpleName();

    private RealmList<Book> books;

    public RealmList<Book> getBooks() {
        return books;
    }

    public void setBooks(RealmList<Book> books) {
        this.books = books;
    }
}
