package com.crio.RentRead.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;

    @Column(nullable = false)
    private boolean availabilityStatus = true;
}

