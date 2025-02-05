package com.pjsh.onlinemovierental.repositories;

import com.pjsh.onlinemovierental.entities.actions.Rental;
import com.pjsh.onlinemovierental.entities.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query("SELECT r FROM Rental r WHERE r.customer = :customer AND r.video.title = :title")
    List<Rental> findRentalsByCustomerAndTitle(Customer customer, String title);

    @Query("SELECT r FROM Rental r " +
            "JOIN r.video v " +
            "JOIN TVShowSeason t ON v.id = t.id " +
            "WHERE r.customer = :customer " +
            "AND v.title = :title " +
            "AND t.seasonNumber = :seasonNumber")
    List<Rental> findTvShowSeasonRentalByCustomerAndTitle(Customer customer, String title, int seasonNumber);

    List<Rental> findRentalsByCustomer(Customer customer);
    List<Rental> findRentalsByStatus(String string);

    @Modifying
    @Query("UPDATE Rental r SET r.returnDate = :returnDate, r.status = :status WHERE r.id = :rentalId")
    void updateRental(Long rentalId, LocalDate returnDate, String status);

}
