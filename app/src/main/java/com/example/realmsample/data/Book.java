package com.example.realmsample.data;

import io.realm.RealmObject;

/**
 * Created by snishimura on 16/07/03.
 */
public class Book extends RealmObject {
    private static final String TAG = Book.class.getSimpleName();

    private String title;
    private String author;
    private int pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
