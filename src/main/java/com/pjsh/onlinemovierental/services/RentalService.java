package com.pjsh.onlinemovierental.services;

import com.pjsh.onlinemovierental.entities.users.Customer;
import com.pjsh.onlinemovierental.entities.videos.Video;
import com.pjsh.onlinemovierental.enums.SearchOption;
import com.pjsh.onlinemovierental.repositories.RentalRepository;
import com.pjsh.onlinemovierental.repositories.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String rentVideo(Customer customer, Video video) {
        // Create a rental
//        Rental rental = new Rental(customer, video);
//        rental.setRentalDate(LocalDate.now());
//        rental.setReturnDate(LocalDate.now().plusDays(7));
//        rental.setStatus(RentalStatus.ACTIVE.toString());
//
//        rentalRepository.save(rental);
//        video.setCopies(video.getCopies() - 1);
//
//        return "Successfully rented " + video.getTitle();
        return "Created new rental entry!";
    }
}
