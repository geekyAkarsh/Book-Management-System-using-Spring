package com.akarshacciojob.bookManagement;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class BookController {

    Map<Integer,Book> data = new HashMap<>();

    @PostMapping("/add-book")
    public String addBook(@RequestBody Book book){

        data.put(book.getBookId(),book);
        return "book with id - " + book.getBookId() + " added successfully";
    }

    @GetMapping("/find-book")
    public Book findBook(@RequestParam("bookId") Integer id){

        return data.get(id);
    }

    @GetMapping("/find-book/{id}")
    public Book findBookID(@PathVariable Integer id){

        return data.get(id);
    }


    @PutMapping("/update-book")
    public String updateBook(@RequestParam int id,@RequestParam String title,
                             @RequestParam String author,@RequestParam int pages)
    {
        Book book = data.get(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPages(pages);
        data.put(id,book);

        return "book updated !!!";
    }

    @PutMapping("/update-book/{id}")
    public String updateBookOptional(@PathVariable Integer id,@RequestParam(required = false) String title,
                                     @RequestParam(required = false) String author,
                                     @RequestParam(required = false) int pages)
    {
        Book book = data.get(id);
        if(Objects.nonNull(title)){
            book.setTitle(title);
        }

        if(Objects.nonNull(author)){
            book.setAuthor(author);
        }

        if(Objects.nonNull(pages)){
            book.setPages(pages);
        }
        data.put(id,book);

        return "book information updated successfully !!!";
    }

    @DeleteMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable int id){

        data.remove(id);
        return "Book removed from database successfully";
    }
}
