package com.pjsh.onlinemovierental.entities.videos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Movie")
public class Movie extends Video{
    private int duration;

    public Movie() {
        super();
    }

    public Movie(String title, String genre, String releaseYear, boolean isAvailable, int copies, int duration) {
        super(title, genre, releaseYear, isAvailable, copies);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
