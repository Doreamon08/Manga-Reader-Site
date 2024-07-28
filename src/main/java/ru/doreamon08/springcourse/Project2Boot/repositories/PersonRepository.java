package ru.doreamon08.springcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.doreamon08.springcourse.Project2Boot.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
//    void updatePersonById(int id, Person person);
}
