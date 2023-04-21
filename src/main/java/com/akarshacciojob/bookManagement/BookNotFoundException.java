package com.akarshacciojob.bookManagement;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(int id) {
        super("Book with id : " + id + " not found in the directory");
    }
}
