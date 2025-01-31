package com.pjsh.onlinemovierental.entities.videos;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Movie extends Video{
    private int duration;

    public Movie() {
        super();
    }

    public Movie(Long id, String title, String genre, LocalDate releaseDate, double rating, boolean isAvailable, int copies, int duration) {
        super(id, title, genre, releaseDate, rating, isAvailable, copies);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
