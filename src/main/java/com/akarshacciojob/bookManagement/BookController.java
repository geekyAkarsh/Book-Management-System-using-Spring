package com.akarshacciojob.bookManagement;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {

    BookService bookService = new BookService();
    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestBody Book book){

        try{
            Boolean added = bookService.addBook(book);
            return new ResponseEntity("Book added Successfully to the Library!!!", HttpStatus.CREATED);
        }catch (BookAlreadyExistsException ex){
            return new ResponseEntity("Book already Exists in the Library!!",HttpStatus.valueOf(400));
        } catch (Exception ex){
            return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/find-book")
    public ResponseEntity findBook(@RequestParam("bookId") Integer id){

        try{
            Optional<Book> book = bookService.getBook(id);
            return new ResponseEntity(book.get(),HttpStatus.OK);
        }catch (BookNotFoundException ex){
            return new ResponseEntity("Book not found in Library",HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }
    }

    /*
    @GetMapping("/find-book/{id}")
    public Book findBookID(@PathVariable Integer id){

        return data.get(id);
    }

    @GetMapping("/all-books")
    public List<Book> findBooks(){
        return data.values().stream().toList();
    }

    @PutMapping("/update-book")
    public String updateBook(@RequestParam int id,@RequestParam String title,
                             @RequestParam String author,@RequestParam Integer pages)
    {
        Book book = data.get(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPages(pages);
        data.put(id,book);

        return "book updated !!!";
    }
    */

    @PutMapping("/update-book/{id}")
    public ResponseEntity updateBookOptional(@PathVariable Integer id,@RequestParam(required = false) String title,
                                     @RequestParam(required = false) String author,
                                     @RequestParam(required = false) Integer pages)
    {
        try{
            Optional<Book> book = bookService.updateBook(id,title,author,pages);
            return new ResponseEntity(book.get(),HttpStatus.OK);
        }catch (BookNotFoundException ex){
            return new ResponseEntity("Book does not exist in Library!!!",HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }
    }

    @DeleteMapping("/remove-book/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){

        try{
            Boolean removed = bookService.deleteBook(id);
            return new ResponseEntity("Book deleted from Library",HttpStatus.OK);
        } catch (BookNotFoundException ex){
            return new ResponseEntity("Book does not exist in Library!!!",HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }
    }
}
