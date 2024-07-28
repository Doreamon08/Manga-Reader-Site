package ru.doreamon08.springcourse.Project2Boot.models;


import jakarta.persistence.*;

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

    @OneToMany(mappedBy = "mangaOwner")
    private List<Chapter> chapters;

    public Manga() {
    }

    public Manga(String title, String author, String description, String linkToPreviewImage) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.linkToPreviewImage = linkToPreviewImage;
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
