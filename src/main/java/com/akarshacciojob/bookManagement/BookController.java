package com.akarshacciojob.bookManagement;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookController {

    Map<Integer,Book> data = new HashMap<>();
//    Book book1 = new Book(1,"a","b",20);
//    data.put(book1.id,book1);

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

    @DeleteMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable int id){

        data.remove(id);
        return "Book removed from database successfully";
    }
}
