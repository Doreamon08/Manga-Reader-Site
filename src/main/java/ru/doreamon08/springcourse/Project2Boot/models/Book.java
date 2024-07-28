package ru.doreamon08.springcourse.Project2Boot.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private int year;

    @Column(name = "date_take")
    private Date date_take;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    @Transient
    private boolean delay;

    public Book(String name, String author, int year, Person owner) {
        this.owner = owner;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public boolean isDelay() {
        if ((new Date().getTime() - date_take.getTime()) > 864000000)
            setDelay(true);
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }

    public Date getDate_take() {
        return date_take;
    }

    public void setDate_take(Date date_take) {
        this.date_take = date_take;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
