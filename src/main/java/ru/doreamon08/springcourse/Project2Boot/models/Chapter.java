package ru.doreamon08.springcourse.Project2Boot.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "chapters")
public class Chapter {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "chapterOwner")
    private List<ChapterPage> pages;

    @ManyToOne
    @JoinColumn(name = "manga_id", referencedColumnName = "id")
    private Manga mangaOwner;

    public Chapter(String title, int number, List<ChapterPage> pages, Manga mangaOwner) {
        this.title = title;
        this.number = number;
        this.pages = pages;
        this.mangaOwner = mangaOwner;
    }

    public Chapter() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<ChapterPage> getPages() {
        return pages;
    }

    public void setPages(List<ChapterPage> pages) {
        this.pages = pages;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Manga getMangaOwner() {
        return mangaOwner;
    }

    public void setMangaOwner(Manga mangaOwner) {
        this.mangaOwner = mangaOwner;
    }
}
