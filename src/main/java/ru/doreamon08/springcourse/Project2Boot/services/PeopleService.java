package ru.doreamon08.springcourse.Project2Boot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.doreamon08.springcourse.Project2Boot.models.Person;
import ru.doreamon08.springcourse.Project2Boot.repositories.PersonRepository;


import java.util.List;

@Service
@Transactional
public class PeopleService {

    private final PersonRepository personRepository;

    public PeopleService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void update(int id, Person person) {
        person.setId(id);
        personRepository.saveAndFlush(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
