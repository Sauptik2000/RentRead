package com.crio.RentRead.controller;

import com.crio.RentRead.model.Book;
import com.crio.RentRead.model.Rental;
import com.crio.RentRead.model.User;
import com.crio.RentRead.repository.BookRepository;
import com.crio.RentRead.repository.RentalRepository;
import com.crio.RentRead.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class RentalController {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public RentalController(RentalRepository rentalRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/{bookId}/rent")
    public ResponseEntity<String> rentBook(@PathVariable Long bookId, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Rental> activeRentals = rentalRepository.findByUserAndReturnDateIsNull(user);

        if (activeRentals.size() >= 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Maximum of 2 active rentals allowed");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailabilityStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not available for rent");
        }

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        book.setAvailabilityStatus(false);
        bookRepository.save(book);
        rentalRepository.save(rental);

        return ResponseEntity.status(HttpStatus.CREATED).body("Book rented successfully");
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rental rental = rentalRepository.findByUserAndReturnDateIsNull(user).stream()
                .filter(r -> r.getBook().getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        rental.setReturnDate(java.time.LocalDate.now());
        Book book = rental.getBook();
        book.setAvailabilityStatus(true);
        bookRepository.save(book);
        rentalRepository.save(rental);

        return ResponseEntity.ok("Book returned successfully");
    }
}