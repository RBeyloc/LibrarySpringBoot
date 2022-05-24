package io.company.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        Optional<Iterable<Book>> booksRetrieved = bookservice.getAllBooks();

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "books");

        if (booksRetrieved.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(booksRetrieved.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: create
    @PostMapping(path = "addBook", consumes = "application/JSON")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Optional<Book> bookCreated = bookservice.createBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "addBook");

        if (bookCreated.isPresent()) {
            headers.add("operationStatus", "created");
            return ResponseEntity.accepted().headers(headers).body(bookCreated.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: read, find one book by id
    @GetMapping(path = "getBook")
    public ResponseEntity<Book> findBookById(@RequestParam Long bookId) {
        Optional<Book> bookFound = bookservice.findBookById(bookId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getBook");

        if (bookFound.isPresent()) {
            headers.add("operationStatus", "found");
            return ResponseEntity.accepted().headers(headers).body(bookFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: delete book by id
    @DeleteMapping(path = "deleteBook")
    public ResponseEntity<Book> deleteBook(@RequestParam Long bookId) {
        Optional<Book> bookFound = bookservice.deleteBookById(bookId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteBook");

        if (bookFound.isPresent()) {
            headers.add("operationStatus", "deleted");
            return ResponseEntity.accepted().headers(headers).body(bookFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: update
    @PutMapping(path = "updateBook", consumes = "application/JSON")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Optional<Book> bookFound = bookservice.findBookById(book.getBookId());

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "updateBook");

        if (!bookFound.isPresent()) {
            headers.add("operationStatus", "not found");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else if (book.equals(bookFound)) {
            headers.add("operationStatus", "no data to update");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else {
            headers.add("operationStatus", "updated");
            return ResponseEntity.accepted().headers(headers).body(bookservice.updateBook(book).get());
        }
    }

    //CRUD: read, find one book by title
    @GetMapping(path = "getBookByTitle")
    public ResponseEntity<Iterable<Book>> findBooksByTitle(@RequestParam String title) {
        Optional<Iterable<Book>> booksFound = bookservice.findBooksByTitle(title);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getBookByTitle");

        if (booksFound.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(booksFound.get());
        } else {
            headers.add("operationStatus", "not Found");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: delete by title
    @DeleteMapping(path = "deleteBookByTitle")
    public ResponseEntity<Book> deleteBookByTitle(@RequestParam String title) {
        Optional<Iterable<Book>> booksFound = bookservice.findBooksByTitle(title);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteBookByTitle");

        if (booksFound.isPresent()) {
            headers.add("operationStatus", "deleted successfully");
            Optional<Book> bookDeleted = bookservice.deleteBookByTitle(title);
            return ResponseEntity.accepted().body(bookDeleted.get());
        } else {
            headers.add("operationStatus", "not Found");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

}
