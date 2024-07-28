package ru.doreamon08.springcourse.Project2Boot.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.doreamon08.springcourse.Project2Boot.models.Book;
import ru.doreamon08.springcourse.Project2Boot.models.Person;
import ru.doreamon08.springcourse.Project2Boot.repositories.BookRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BooksService {

    private final BookRepository bookRepository;

    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAll(int page, int booksPerPage, boolean sortByYear) {
        List<Book> all = bookRepository.findAll();
        if (sortByYear)
            all = bookRepository.findAll(Sort.by("year"));
        if (booksPerPage != 0)
            all = bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        if (booksPerPage != 0 && sortByYear)
            all = bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        return all;
    }

    public Book findOne(int id) {

        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findBooksByPersonId(Person person) {
        return bookRepository.findAllByOwner(person);
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public void update(int id, Book book) {
        book.setBook_id(id);
        bookRepository.save(book);
    }

    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public void free(int id) {
        findOne(id).setOwner(null);
    }

    public void assign(int id, Person person) {
        findOne(id).setOwner(person);
        findOne(id).setDate_take(new Date());
    }

    public void createTests() {
        for (int i = 0; i < 100; i++)
        {
            Book book = new Book(
                    "testName" + i,
                    "testAuthor" + i,
                    1900 + i,
                    null);
            bookRepository.save(book);
        }
    }

    public List<Book> findByName(String bookName) {
        return bookRepository.findByNameStartingWith(bookName);
    }
}
