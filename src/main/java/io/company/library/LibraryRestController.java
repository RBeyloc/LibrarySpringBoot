package io.company.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> findBookById(@RequestParam Long bookId){
        Optional<Book> bookFound = bookservice.findBookById(bookId);
        if (bookFound.isPresent()) {
            return ResponseEntity.accepted().body(bookFound.get().toString());
        } else  {
            return ResponseEntity.accepted().body("Fail: Book id not found");
        }
    }

    //CRUD: delete book by id
    @DeleteMapping(path="deleteBook")
    public ResponseEntity<String> deleteBook (@RequestParam Long bookId){
        Optional<Book> bookFound = bookservice.deleteBookById(bookId);
        if(bookFound.isPresent()) {
            return ResponseEntity.accepted().body(bookFound.get().toString());
        } else {
            return ResponseEntity.accepted().body("Fail: Book id not found");
        }
    }

    //CRUD: update
    @PutMapping(path="updateBook", consumes = "application/JSON")
    public ResponseEntity<String> updateBook(@RequestBody Book book){
        Optional<Book> bookFound = bookservice.findBookById(book.getBookId());
        if (!bookFound.isPresent()) {
            return ResponseEntity.accepted().body("Fail: Book to update not found");
        } else if(book.equals(bookFound)) {
            return ResponseEntity.accepted().body("Fail: No changes on book to be updates");
        } else {
            return ResponseEntity.accepted().body(bookservice.updateBook(book).toString());
        }
    }

    //CRUD: read, find one book by title
    @GetMapping(path="getBookByTitle")
    public ResponseEntity<String> findBookByTitle(@RequestParam String title){
        Optional<Book> bookFound = bookservice.findBookByTitle(title);
        if (bookFound.isPresent()) {
            return ResponseEntity.accepted().body(bookFound.get().toString());
        } else  {
            return ResponseEntity.accepted().body("Fail: Title not found");
        }
    }

    //CRUD: delete by title
    @DeleteMapping(path="deleteBookByTitle")
    public ResponseEntity<String> deleteBookByTitle (@RequestParam String title){
        Optional<Book> bookFound = bookservice.findBookByTitle(title);
        if(bookFound.isPresent()) {
            bookservice.deleteBookByTitle(title);
            return ResponseEntity.accepted().body(bookFound.get().toString());
        } else {
            return ResponseEntity.accepted().body("Fail: Title not found");
        }
    }

}
