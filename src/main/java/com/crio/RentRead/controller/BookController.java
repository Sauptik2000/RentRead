package com.crio.RentRead.controller;

import com.crio.RentRead.model.Book;
import com.crio.RentRead.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findByAvailabilityStatus(true));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
        return ResponseEntity.noContent().build();
    }
}