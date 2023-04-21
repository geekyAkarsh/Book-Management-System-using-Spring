package com.akarshacciojob.bookManagement;

import com.fasterxml.jackson.datatype.jdk8.OptionalDoubleSerializer;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

public class BookService {

    BookRepository bookRepository = new BookRepository();
    public Boolean addBook(Book book) throws BookAlreadyExistsException{

        Optional<Book> bookOpt = bookRepository.getById(book.getBookId());
        if(bookOpt.isPresent()){
            throw new BookAlreadyExistsException(book.getBookId());
        }

        return bookRepository.addBook(book);
    }


    public Optional<Book> getBook(Integer id) throws BookNotFoundException{

        Optional<Book> bookOpt = bookRepository.getById(id);

        if(bookOpt.isEmpty()){
            throw new BookNotFoundException(id);
        }

        return bookOpt;
    }

    public Optional<Book> updateBook(Integer id, String title, String author, Integer pages) throws BookNotFoundException{

        Book book = getBook(id).get();

        if(Objects.nonNull(title)){
            book.setTitle(title);
        }
        if(Objects.nonNull(author)){
            book.setAuthor(author);
        }
        if(Objects.nonNull(pages)){
            book.setPages(pages);
        }

        bookRepository.addBook(book);
        return Optional.of(book);
    }

    public Boolean deleteBook(int id) throws BookNotFoundException{

        Book book = getBook(id).get();

        bookRepository.removeById(id);
        return true;
    }
}
