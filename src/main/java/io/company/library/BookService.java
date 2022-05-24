package io.company.library;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Iterable<Book> getAllBooks() {
        Iterable<Book> books = bookRepository.findAll();
        return books;
    }

    public Optional<Book> createBook (Book book){
        Book bookCreated = bookRepository.save(book);
        return Optional.of(bookCreated);
    }

    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    public Optional<Book> deleteBookById(Long id){
        //Find out IF this id-book IS in our DB
        Optional<Book> bookFound = bookRepository.findById(id);
        if(bookFound.isPresent()) {
            bookRepository.deleteById(id);
            return Optional.of(bookFound.get());
        } else {
            return null;
        }
    }

    public Book updateBook(Book book) {
        Book bookUpdated = bookRepository.save(book);
        return bookUpdated;
    }

    public Optional<Book> findBookByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }

    public Optional<Book> deleteBookByTitle(String title){
        //Find out IF this id-book IS in our DB
        Optional<Book> bookFound = bookRepository.findBookByTitle(title);
        if(bookFound.isPresent()) {
            bookRepository.deleteBookByTitle(title);
            return Optional.of(bookFound.get());
        } else {
            return null;
        }
    }



}