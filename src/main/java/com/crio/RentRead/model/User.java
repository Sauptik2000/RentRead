package com.crio.RentRead.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public enum Role {
        USER,
        ADMIN
    }
}
