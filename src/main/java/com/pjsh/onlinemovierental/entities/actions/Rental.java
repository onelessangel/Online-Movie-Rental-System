package com.pjsh.onlinemovierental.entities.actions;

import com.pjsh.onlinemovierental.entities.users.Customer;
import com.pjsh.onlinemovierental.entities.videos.Video;
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
    private Video video;

    private LocalDate rentalDate;
    private LocalDate returnDate;
    private String status;

    public Rental() {
    }

    public Rental(Customer customer, Video vIdeo) {
        this.customer = customer;
        this.video = vIdeo;
    }

    public Rental(Customer testCustomer, Video testMovie, String active) {
        this.customer = testCustomer;
        this.video = testMovie;
        this.status = active;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
