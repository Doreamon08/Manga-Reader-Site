package ru.doreamon08.springcourse.Project2Boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.doreamon08.springcourse.Project2Boot.models.Person;
import ru.doreamon08.springcourse.Project2Boot.services.BooksService;
import ru.doreamon08.springcourse.Project2Boot.services.PeopleService;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final BooksService booksService;
    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("{person_id}")
    public String show(@PathVariable("person_id") int person_id, Model model) {
        model.addAttribute("person", peopleService.findOne(person_id));

        model.addAttribute("books", booksService.findBooksByPersonId(peopleService.findOne(person_id)));

        return "people/show";
    }

    @GetMapping ("/new")//сайт
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";//где лежит наша форма html
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public  String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
//        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }


}
