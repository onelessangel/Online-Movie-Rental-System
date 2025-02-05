package com.pjsh.onlinemovierental;

import com.pjsh.onlinemovierental.entities.actions.Rental;
import com.pjsh.onlinemovierental.entities.users.Customer;
import com.pjsh.onlinemovierental.entities.videos.Movie;
import com.pjsh.onlinemovierental.entities.videos.TVShowSeason;
import com.pjsh.onlinemovierental.entities.videos.Video;
import com.pjsh.onlinemovierental.enums.RentalStatus;
import com.pjsh.onlinemovierental.repositories.RentalRepository;
import com.pjsh.onlinemovierental.repositories.VideoRepository;
import com.pjsh.onlinemovierental.services.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RentalServiceTest {
    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private RentalService rentalService;

    private static final String MOVIE_TITLE = "The Matrix";
    private static final String TV_SHOW_SEASON_TITLE = "Breaking Bad";

    private Customer testCustomer;
    private Video testMovie;
    private Video testTVShowSeason;
    private Rental testRentalActive;
    private Rental testRentalReturned;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testCustomer = new Customer();
        testMovie = new Movie(MOVIE_TITLE, "1999", "Action", true, 2, 136);
        testTVShowSeason = new TVShowSeason(TV_SHOW_SEASON_TITLE, "2008", "Drama", true, 2, 5, 13, 45);
        testRentalActive = new Rental(testCustomer, testMovie, RentalStatus.ACTIVE.toString());
        testRentalReturned = new Rental(testCustomer, testMovie, RentalStatus.RETURNED.toString());
    }

    @Test
    void testRentVideoMovie() {
        when(videoRepository.findByTitle(MOVIE_TITLE)).thenReturn(new ArrayList<>(List.of(testMovie)));
        when(rentalRepository.save(any(Rental.class))).thenReturn(new Rental());

        boolean result = rentalService.rentVideo(testCustomer, MOVIE_TITLE, null);

        assertTrue(result);

        verify(videoRepository, times(1)).findByTitle(anyString());
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void testRentVideoTvShowSeason() {
        when(videoRepository.findByTitle(TV_SHOW_SEASON_TITLE)).thenReturn(new ArrayList<>(List.of(testTVShowSeason)));
        when(rentalRepository.save(any(Rental.class))).thenReturn(new Rental());

        boolean resultCorrect = rentalService.rentVideo(testCustomer, TV_SHOW_SEASON_TITLE, 5);
        boolean resultIncorrect = rentalService.rentVideo(testCustomer, TV_SHOW_SEASON_TITLE, 1);

        assertTrue(resultCorrect);
        assertFalse(resultIncorrect);

        verify(videoRepository, times(2)).findByTitle(anyString());
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void testGetRentals() {
        when(rentalRepository.findRentalsByCustomer(testCustomer))
                .thenReturn(new ArrayList<>(List.of(testRentalActive, testRentalReturned)));

        List<Rental> rentals = rentalService.getRentals(testCustomer);

        assertNotNull(rentals);
        assertEquals(2, rentals.size());
    }

    @Test
    void testGetAllActiveRentals() {
        when(rentalRepository.findRentalsByStatus(RentalStatus.ACTIVE.toString()))
                .thenReturn(new ArrayList<>(List.of(testRentalActive, testRentalActive)));

        List<Rental> rentals = rentalService.getAllActiveRentals();

        assertNotNull(rentals);
        assertEquals(2, rentals.size());
    }

    @Test
    void testGetActiveRentals() {
        when(rentalRepository.findRentalsByCustomer(testCustomer))
                .thenReturn(new ArrayList<>(List.of(testRentalActive, testRentalReturned, testRentalActive)));

        List<Rental> rentals = rentalService.getActiveRentals(testCustomer);

        assertNotNull(rentals);
        assertEquals(2, rentals.size());
    }

    @Test
    void testGetActiveRentalsCount() {
        Customer customer = new Customer();
        when(rentalRepository.findRentalsByCustomer(customer))
                .thenReturn(new ArrayList<>(List.of(testRentalActive, testRentalActive)));

        int count = rentalService.getActiveRentalsCount(customer);

        assertEquals(2, count);
    }

    @Test
    void testCheckRental() {
        when(rentalRepository.findRentalsByCustomerAndTitle(testCustomer, MOVIE_TITLE))
                .thenReturn(new ArrayList<>(List.of(testRentalActive)));
        when(rentalRepository.findTvShowSeasonRentalByCustomerAndTitle(testCustomer, TV_SHOW_SEASON_TITLE, 5))
                .thenReturn(new ArrayList<>(List.of(testRentalActive)));

        boolean resultMovie = rentalService.checkRental(testCustomer, MOVIE_TITLE, null);
        boolean resultTVShowSeason = rentalService.checkRental(testCustomer, TV_SHOW_SEASON_TITLE, 5);

        assertTrue(resultMovie);
        assertTrue(resultTVShowSeason);
    }

    @Test
    void testReturnVideo() {
        when(rentalRepository.findRentalsByCustomerAndTitle(testCustomer, MOVIE_TITLE))
                .thenReturn(new ArrayList<>(List.of(testRentalActive)));
        when(rentalRepository.findTvShowSeasonRentalByCustomerAndTitle(testCustomer, TV_SHOW_SEASON_TITLE, 5))
                .thenReturn(new ArrayList<>(List.of(testRentalActive)));
        when(rentalRepository.save(any(Rental.class))).thenReturn(testRentalReturned);

        boolean resultMovie = rentalService.returnVideo(testCustomer, MOVIE_TITLE, null);
        boolean resultTVShowSeason = rentalService.returnVideo(testCustomer, TV_SHOW_SEASON_TITLE, 5);

        assertTrue(resultMovie);
        assertTrue(resultTVShowSeason);

        verify(rentalRepository, times(1)).findRentalsByCustomerAndTitle(any(Customer.class), anyString());
        verify(rentalRepository, times(1)).findTvShowSeasonRentalByCustomerAndTitle(any(Customer.class), anyString(), anyInt());
        verify(rentalRepository, times(2)).save(any(Rental.class));
    }

}
