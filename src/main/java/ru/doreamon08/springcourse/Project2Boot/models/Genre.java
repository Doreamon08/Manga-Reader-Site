package ru.doreamon08.springcourse.Project2Boot.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Manga> mangas;

    public Genre(String name, List<Manga> mangas) {
        this.name = name;
        this.mangas = mangas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(List<Manga> mangas) {
        this.mangas = mangas;
    }
}
