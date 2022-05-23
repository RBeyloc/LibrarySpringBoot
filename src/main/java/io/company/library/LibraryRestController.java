package io.company.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(path="getBook")
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
    @PutMapping(path="updateBook", consumes = "application/JSON")
    public Optional<Book> updateBook(@RequestBody Book book){
        Optional<Book> bookFound = bookservice.findBookById(book.getBookId());
        if (bookFound.isPresent()) {
            return Optional.ofNullable(bookservice.updateBook(book));
        } else  {
            return null;
        }
    }

}
