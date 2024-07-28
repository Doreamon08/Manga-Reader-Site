package ru.doreamon08.springcourse.Project2Boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.doreamon08.springcourse.Project2Boot.models.Book;
import ru.doreamon08.springcourse.Project2Boot.models.Person;
import ru.doreamon08.springcourse.Project2Boot.services.BooksService;
import ru.doreamon08.springcourse.Project2Boot.services.PeopleService;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("books", booksService.findAll());
//        return "books/index";
//    }

    @GetMapping()
    public String index(
            Model model,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "books_per_page", required = false, defaultValue = "0") int booksPerPage,
            @RequestParam(name = "sort_by_year", required = false, defaultValue = "false") boolean sortByYear) {

        model.addAttribute("books", booksService.findAll(page, booksPerPage, sortByYear));
        return "books/index";
    }


    @GetMapping("{book_id}")
    public String show(@PathVariable("book_id") int book_id, @ModelAttribute("person") Person person,
                       Model model) {
        model.addAttribute("book", booksService.findOne(book_id));
        model.addAttribute("person", booksService.findOne(book_id).getOwner());
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("new_book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("new_book") Book book) {
        booksService.save(book);
        return "redirect:/books";
    }

    @PostMapping("/createTests")
    public String createTest() {
        booksService.createTests();
        return "redirect:/books";
    }

    @GetMapping("/{book_id}/edit")
    public String edit(@PathVariable("book_id") int book_id, Model model) {
        model.addAttribute("book", booksService.findOne(book_id));
        return "books/edit";
    }

    @PatchMapping("/{book_id}")
    public String update(@ModelAttribute("book") Book book,
                         @PathVariable("book_id") int book_id) {
        booksService.update(book_id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{book_id}/free")
    public String free(@PathVariable("book_id") int book_id) {
        booksService.free(book_id);
        return "redirect:/books/{book_id}";
    }

    @PatchMapping("/{book_id}/assign")
    public String assign(@PathVariable("book_id") int bookId,
                         @RequestParam("personId") int personId) {
        Person person = peopleService.findOne(personId);

        booksService.assign(bookId, person);

        return "redirect:/books/{book_id}";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "/books/search";
    }

    @PostMapping("/search")
    public String findBook(@RequestParam("book_name") String bookName,
                           Model model) {

        model.addAttribute("books", booksService.findByName(bookName));
        return "books/search"; // Возвращаем имя шаблона для отображения результата поиска
    }

}
