package com.akarshacciojob.bookManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookRepository {

    private Map<Integer,Book> data = new HashMap<>();

    public Boolean addBook(Book book) {

        data.put(book.getBookId(),book);
        return true;
    }

    public Optional<Book> getById(int bookId) {

        if(data.containsKey(bookId))
            return Optional.of(data.get(bookId));

        return Optional.empty();
    }

    public void removeById(int id) {

        data.remove(id);
        return;
    }
}
