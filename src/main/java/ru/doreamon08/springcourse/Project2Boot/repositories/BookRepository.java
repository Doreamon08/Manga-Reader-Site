package ru.doreamon08.springcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.doreamon08.springcourse.Project2Boot.models.Book;
import ru.doreamon08.springcourse.Project2Boot.models.Person;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    public List<Book> findAllByOwner(Person person);
    List<Book> findByNameStartingWith(String name);
}
