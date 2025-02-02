package com.pjsh.onlinemovierental.entities.videos;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// TODO: nu am fol MappedSuperclass pentru ca in Rental exista o referinta la Video si nu se poate face o mapare ManyToOne catre o clasa MappedSuperclass
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String releaseYear;
    private boolean isAvailable;
    private int copies;

    public Video() {
    }

    public Video(String title, String genre, String releaseYear, boolean isAvailable, int copies) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.isAvailable = isAvailable;
        this.copies = copies;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}
