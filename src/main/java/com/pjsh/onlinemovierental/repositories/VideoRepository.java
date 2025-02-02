package com.pjsh.onlinemovierental.repositories;

import com.pjsh.onlinemovierental.entities.videos.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    ArrayList<Video> findByTitle(String title);
    ArrayList<Video> findVideosByGenre(String genre);
    ArrayList<Video> findVideosByTitleContaining(String title);
    ArrayList<Video> findVideosByReleaseYear(String releaseYear);
}
