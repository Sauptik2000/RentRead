package com.crio.RentRead.service;

import com.crio.RentRead.model.Book;
import com.crio.RentRead.model.Rental;
import com.crio.RentRead.model.User;
import com.crio.RentRead.repository.BookRepository;
import com.crio.RentRead.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;

    public RentalService(RentalRepository rentalRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
    }

    public List<Rental> findActiveRentalsByUser(User user) {
        return rentalRepository.findByUserAndReturnDateIsNull(user);
    }

    public Rental rentBook(User user, Book book) {
        // Check if the user has less than 2 active rentals
        List<Rental> activeRentals = findActiveRentalsByUser(user);
        if (activeRentals.size() >= 2) {
            throw new IllegalStateException("User cannot have more than 2 active rentals.");
        }

        // Check if the book is available
        if (!book.isAvailabilityStatus()) {
            throw new IllegalStateException("Book is not available for rent.");
        }

        // Rent the book
        book.setAvailabilityStatus(false);
        bookRepository.save(book);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentalDate(LocalDate.now());

        return rentalRepository.save(rental);
    }

    public Rental returnBook(User user, Book book) {
        // Find the active rental for this user and book
        Rental rental = rentalRepository.findByUserAndReturnDateIsNull(user).stream()
                .filter(r -> r.getBook().getId().equals(book.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Rental not found for this book and user."));

        // Return the book
        rental.setReturnDate(LocalDate.now());
        rentalRepository.save(rental);

        // Update the book availability status
        book.setAvailabilityStatus(true);
        bookRepository.save(book);

        return rental;
    }
}