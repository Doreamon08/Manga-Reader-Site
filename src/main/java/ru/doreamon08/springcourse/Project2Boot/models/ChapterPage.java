package ru.doreamon08.springcourse.Project2Boot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "chapter_pages")
public class ChapterPage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "page_number")
    private int pageNumber;

    @Column(name = "page_url")
    private int pageUrl;

    @ManyToOne
    @JoinColumn(name = "chapter_id", referencedColumnName = "id")
    private Chapter chapterOwner;

    public ChapterPage(int pageNumber, int pageUrl, Chapter chapterOwner) {
        this.pageNumber = pageNumber;
        this.pageUrl = pageUrl;
        this.chapterOwner = chapterOwner;
    }

    public ChapterPage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(int pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Chapter getChapterOwner() {
        return chapterOwner;
    }

    public void setChapterOwner(Chapter chapterOwner) {
        this.chapterOwner = chapterOwner;
    }
}
