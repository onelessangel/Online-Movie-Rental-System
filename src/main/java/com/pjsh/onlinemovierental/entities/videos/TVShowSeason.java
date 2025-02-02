package com.pjsh.onlinemovierental.entities.videos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TVShowSeason")
public class TVShowSeason extends Video {
    private int seasonNumber;
    private int episodeCount;
    private int episodeDuration;

    public TVShowSeason() {
        super();
    }

    public TVShowSeason(String title, String genre, String releaseYear, boolean isAvailable,
                        int copies, int seasonNumber, int episodeCount, int episodeDuration) {
        super(title, genre, releaseYear, isAvailable, copies);
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
        this.episodeDuration = episodeDuration;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public int getEpisodeDuration() {
        return episodeDuration;
    }

    public void setEpisodeDuration(int episodeDuration) {
        this.episodeDuration = episodeDuration;
    }
}
