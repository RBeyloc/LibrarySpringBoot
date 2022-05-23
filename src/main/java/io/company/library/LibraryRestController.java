package io.company.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class LibraryRestController {

    @Autowired
    BookService bookservice;

    //here we are creating our end-point rest API
    //CRUD: read
    @GetMapping("books")
    public Iterable<Book> getAllBooks() {
        return bookservice.getAllBooks();
    }

    //CRUD: read, find one book by id
    @GetMapping("getBook")
    public Optional<Book> findBookById(Long id){
        Optional<Book> bookFound = bookservice.findBookById(id);
        if (bookFound.isPresent()) {
            return Optional.of(bookFound.get());
        } else  {
            return null;
        }
    }

    //CRUD: create
    @PostMapping("addBook")
    public Book addBook(@RequestBody Book book){
        Book bookCreated = bookservice.createBook(book);
        return bookCreated ;
    }

    //CRUD: delete
    @DeleteMapping("deleteBook")
    public Optional<Book> deleteBook (Long id){
        Optional<Book> bookFound = bookservice.findBookById(id);
        if(bookFound.isPresent()) {
            bookservice.deleteBookById(id);
            return Optional.of(bookFound.get());
        } else {
            return null;
        }
    }


    //CRUD: update


}
