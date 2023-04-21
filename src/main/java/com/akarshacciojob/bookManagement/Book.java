package com.akarshacciojob.bookManagement;

import lombok.Getter;
import lombok.Setter;

@Getter// automatically creates getters functions for all data types
@Setter// automatically creates setters functions for all data types
public class Book {

    private int bookId;
    private String title;
    private String author;
    private int pages;

    public Book(int bookId, String title, String author, int pages) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }


}
