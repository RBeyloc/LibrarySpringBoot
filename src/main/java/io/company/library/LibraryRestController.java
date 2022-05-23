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


    //CRUD: create
    @PostMapping(path="addBook", consumes = "application/JSON")
    public Book addBook(@RequestBody Book book){
        Book bookCreated = bookservice.createBook(book);
        return bookCreated ;
    }

    //CRUD: read, find one book by id
    @PostMapping(path="getBook")
    public Optional<Book> findBookById(@RequestParam Long bookId){
        Optional<Book> bookFound = bookservice.findBookById(bookId);
        if (bookFound.isPresent()) {
            return Optional.of(bookFound.get());
        } else  {
            return null;
        }
    }

    //CRUD: delete
    @DeleteMapping(path="deleteBook")
    public Optional<Book> deleteBook (@RequestParam Long bookId){
        Optional<Book> bookFound = bookservice.deleteBookById(bookId);
        if(bookFound.isPresent()) {
            return Optional.of(bookFound.get());
        } else {
            return null;
        }
    }

    //CRUD: update


}
