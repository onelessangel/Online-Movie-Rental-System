package com.pjsh.onlinemovierental.entities.actions;

import com.pjsh.onlinemovierental.entities.users.Customer;
import com.pjsh.onlinemovierental.entities.videos.Movie;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Movie movie;

    private LocalDate rentalDate;
    private LocalDate returnDate;
    private String status;
}
