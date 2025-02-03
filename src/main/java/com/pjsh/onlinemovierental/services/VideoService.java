package com.pjsh.onlinemovierental.services;

import com.pjsh.onlinemovierental.entities.videos.Movie;
import com.pjsh.onlinemovierental.entities.videos.TVShowSeason;
import com.pjsh.onlinemovierental.entities.videos.Video;
import com.pjsh.onlinemovierental.repositories.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Transactional
    public String addMovie(String title, String genre, String releaseYear, int duration, int copies) {
        Movie movie = new Movie(title, genre, releaseYear, true, copies, duration);
        videoRepository.save(movie);
        return "Created new movie";
    }

    @Transactional
    public String addTVShow(String title, String genre, String releaseDate, int seasons, int episodes, int episodeDuration, int copies) {
        TVShowSeason tvShowSeason = new TVShowSeason(title, genre, releaseDate, true, copies, seasons, episodes, episodeDuration);
        videoRepository.save(tvShowSeason);
        return "Created new TV Show Season";
    }

    @Transactional
    public boolean removeVideo(String title, Integer seasonNumber) {
        List<Video> videos = videoRepository.findByTitle(title);
        if (videos.isEmpty()) {
            return false;
        }

        if (seasonNumber != null && seasonNumber != -1) {
            videos.removeIf(video -> !(video instanceof TVShowSeason) || ((TVShowSeason) video).getSeasonNumber() != seasonNumber);
        }

        videoRepository.deleteAll(videos);
        return true;
    }

    public List<Video> viewAllMovies() {
        return videoRepository.findAll().stream().sorted(Comparator.comparing(Video::getTitle)).toList();
    }

    public List<Video> findVideoByTitle(String title) {
        return videoRepository.findVideosByTitleContaining(title)
                .stream().sorted(Comparator.comparing(Video::getTitle)).toList();
    }

    public List<Video> findVideoByGenre(String genre) {
        return videoRepository.findVideosByGenre(genre)
                .stream().sorted(Comparator.comparing(Video::getTitle)).toList();
    }

    public List<Video> findVideoByReleaseYear(String releaseYear) {
        return videoRepository.findVideosByReleaseYear(releaseYear)
                .stream().sorted(Comparator.comparing(Video::getTitle)).toList();
    }
}
