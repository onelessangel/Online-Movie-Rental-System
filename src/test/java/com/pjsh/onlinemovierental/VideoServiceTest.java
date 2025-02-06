package com.pjsh.onlinemovierental;

import com.pjsh.onlinemovierental.entities.videos.Movie;
import com.pjsh.onlinemovierental.entities.videos.TVShowSeason;
import com.pjsh.onlinemovierental.entities.videos.Video;
import com.pjsh.onlinemovierental.repositories.VideoRepository;
import com.pjsh.onlinemovierental.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VideoServiceTest {
    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoService videoService;

    private static final String MOVIE_TITLE = "The Matrix";
    private static final String TV_SHOW_SEASON_TITLE = "Breaking Bad";

    private Video testMovie;
    private Video testTVShowSeason;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testMovie = new Movie(MOVIE_TITLE, "1999", "Action", true, 2, 136);
        testTVShowSeason = new TVShowSeason(TV_SHOW_SEASON_TITLE, "2008", "Drama", true, 2, 5, 13, 45);
    }

    @Test
    void testAddMovie() {
        when(videoRepository.save(any(Movie.class))).thenReturn((Movie) testMovie);

        String result = videoService.addMovie(MOVIE_TITLE, "Action", "1999", 136, 2);

        assertEquals("Created new movie", result);
        verify(videoRepository, times(1)).save(any(Movie.class));
    }

    @Test
    void testAddTVShow() {
        when(videoRepository.save(any(TVShowSeason.class))).thenReturn((TVShowSeason) testTVShowSeason);

        String result = videoService.addTVShow(TV_SHOW_SEASON_TITLE, "Drama", "2008", 5, 13, 45, 2);

        assertEquals("Created new TV Show Season", result);
        verify(videoRepository, times(1)).save(any(TVShowSeason.class));
    }

    @Test
    void testRemoveVideo() {
        when(videoRepository.findByTitle(MOVIE_TITLE)).thenReturn(new ArrayList<>(List.of(testMovie)));
        when(videoRepository.findByTitle(TV_SHOW_SEASON_TITLE)).thenReturn(new ArrayList<>(List.of(testMovie)));

        boolean resultMovie = videoService.removeVideo(MOVIE_TITLE, null);
        boolean resultTVShowSeason = videoService.removeVideo(TV_SHOW_SEASON_TITLE, 1);

        assertTrue(resultMovie);
        assertTrue(resultTVShowSeason);

        verify(videoRepository, times(2)).findByTitle(anyString());
        verify(videoRepository, times(2)).deleteAll(anyList());
    }

    @Test
    void testViewAllVideos() {
        when(videoRepository.findAll()).thenReturn(List.of(testMovie, testTVShowSeason));

        List<Video> videos = videoService.viewAllVideos();

        assertEquals(2, videos.size());
        verify(videoRepository, times(1)).findAll();
    }

    @Test
    void testFindVideoByTitle() {
        when(videoRepository.findVideosByTitleContaining(MOVIE_TITLE)).thenReturn(new ArrayList<>(List.of(testMovie)));

        List<Video> videos = videoService.findVideoByTitle(MOVIE_TITLE);

        assertEquals(1, videos.size());
        verify(videoRepository, times(1)).findVideosByTitleContaining(anyString());
    }

    @Test
    void testFindVideoByGenre() {
        when(videoRepository.findVideosByGenre("Action")).thenReturn(new ArrayList<>(List.of(testMovie)));

        List<Video> videos = videoService.findVideoByGenre("Action");

        assertEquals(1, videos.size());
        verify(videoRepository, times(1)).findVideosByGenre(anyString());
    }

    @Test
    void testFindVideoByReleaseYear() {
        when(videoRepository.findVideosByReleaseYear("1999")).thenReturn(new ArrayList<>(List.of(testMovie)));

        List<Video> videos = videoService.findVideoByReleaseYear("1999");

        assertEquals(1, videos.size());
        verify(videoRepository, times(1)).findVideosByReleaseYear(anyString());
    }
}
