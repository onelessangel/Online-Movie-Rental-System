package com.pjsh.onlinemovierental.services;

import com.pjsh.onlinemovierental.entities.actions.Rental;
import com.pjsh.onlinemovierental.entities.users.Customer;
import com.pjsh.onlinemovierental.entities.videos.TVShowSeason;
import com.pjsh.onlinemovierental.entities.videos.Video;
import com.pjsh.onlinemovierental.enums.RentalStatus;
import com.pjsh.onlinemovierental.enums.SearchOption;
import com.pjsh.onlinemovierental.repositories.RentalRepository;
import com.pjsh.onlinemovierental.repositories.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private VideoRepository videoRepository;

    public RentalService(RentalRepository rentalRepository, VideoRepository videoRepository) {
        this.rentalRepository = rentalRepository;
        this.videoRepository = videoRepository;
    }

    public String searchVideo(SearchOption choice, String videoTitle, String genre, Double rating) {
//        ArrayList<Video> videos;
//
//        switch (choice) {
//            case TITLE:
//                videos = videoRepository.findByTitle(videoTitle);
//                break;
//            case GENRE:
//                videos = videoRepository.findVideosByGenre(genre);
//                break;
//            case PARTIAL_TITLE:
//                videos = videoRepository.findVideosByTitleContaining(videoTitle);
//                break;
//            case RATING:
//                videos = videoRepository.findVideosByRating(rating);
//                break;
//            default:
//                videos = new ArrayList<>();
//                break;
//        }
//
//        return videos;
        return "Searched for video!";
    }

    @Transactional
    public boolean rentVideo(Customer customer, String title, Integer seasonNumber) {
        List<Video> videos;

        if (seasonNumber != null && seasonNumber != -1) {
            videos = videoRepository.findByTitle(title);
            videos.removeIf(video -> !(video instanceof TVShowSeason) || ((TVShowSeason) video).getSeasonNumber() != seasonNumber);
        } else {
            videos = videoRepository.findByTitle(title);
        }

        if (videos.isEmpty()) {
            return false;
        }

        Video video = videos.getFirst();

        Rental rental = new Rental(customer, video);
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(7));
        rental.setStatus(RentalStatus.ACTIVE.toString());

        rentalRepository.save(rental);
        return true;
    }

    public List<Rental> getRentals(Customer customer) {
        return rentalRepository.findRentalsByCustomer(customer);
    }

    public List<Rental> getAllActiveRentals() {
        return rentalRepository.findRentalsByStatus(RentalStatus.ACTIVE.toString());
    }

    public List<Rental> getActiveRentals(Customer customer) {
        return getRentals(customer).stream()
                .filter(rental -> rental.getStatus().equals(RentalStatus.ACTIVE.toString())).toList();
    }

    public int getActiveRentalsCount(Customer customer) {
        return getActiveRentals(customer).size();
    }

    public boolean checkRental(Customer customer, String title, Integer seasonNumber) {
        if (seasonNumber != null && seasonNumber != -1) {
            return !rentalRepository.findTvShowSeasonRentalByCustomerAndTitle(customer, title, seasonNumber).isEmpty();
        }

        return !rentalRepository.findRentalsByCustomerAndTitle(customer, title).isEmpty();
    }

    public boolean returnVideo(Customer customer, String title, Integer seasonNumber) {
        List<Rental> rentals;

        if (seasonNumber != null && seasonNumber != -1) {
            rentals = rentalRepository.findTvShowSeasonRentalByCustomerAndTitle(customer, title, seasonNumber);
        } else {
            rentals = rentalRepository.findRentalsByCustomerAndTitle(customer, title);
        }

        if (rentals.isEmpty()) {
            return false;
        }

        Rental rental = rentals.getFirst();
        rental.setReturnDate(LocalDate.now());
        rental.setStatus(RentalStatus.RETURNED.toString());

        rentalRepository.save(rental);
        return true;
    }
}
