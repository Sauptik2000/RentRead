package com.crio.RentRead.service;

import com.crio.RentRead.model.Book;
import com.crio.RentRead.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllAvailableBooks() {
        return bookRepository.findByAvailabilityStatus(true);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}