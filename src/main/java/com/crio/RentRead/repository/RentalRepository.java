package com.crio.RentRead.repository;

import com.crio.RentRead.model.Rental;
import com.crio.RentRead.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserAndReturnDateIsNull(User user);
}