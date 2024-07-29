package ru.doreamon08.springcourse.Project2Boot.models;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "manga")
public class Manga {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "linktopreviewimage")
    private String linkToPreviewImage;

    @Column(name = "year_of_release")
    private Timestamp yearOfRelease;

    @Column(name = "rating")
    private float rating;

    @Column(name = "original_title")
    private String originalTitle;

    @ManyToMany
    @JoinTable(
            name = "manga_genre",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "manga_tag",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
    @OneToMany(mappedBy = "mangaOwner")
    private List<Chapter> chapters;

    public Manga() {
    }

    public Manga(String title, String author, String description, String linkToPreviewImage, Timestamp yearOfRelease, String originalTitle, List<Genre> genres, List<Tag> tags) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.linkToPreviewImage = linkToPreviewImage;
        this.yearOfRelease = yearOfRelease;
        this.originalTitle = originalTitle;
        this.genres = genres;
        this.tags = tags;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Timestamp getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Timestamp yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkToPreviewImage() {
        return linkToPreviewImage;
    }

    public void setLinkToPreviewImage(String linkToPreviewImage) {
        this.linkToPreviewImage = linkToPreviewImage;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
