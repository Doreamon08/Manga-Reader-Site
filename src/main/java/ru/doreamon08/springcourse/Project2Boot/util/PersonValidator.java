package ru.doreamon08.springcourse.Project2Boot.util;//package ru.doreamon08.SpringMVCTutor2.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//import ru.doreamon08.SpringMVCTutor2.models.Person;
//import ru.doreamon08.SpringMVCTutor2.services.PeopleService;
//
//@Component
//public class PersonValidator implements Validator {
//
//    private final PeopleService peopleService;
//
//    public PersonValidator(PeopleService peopleService) {
//        this.peopleService = peopleService;
//    }
//
//    @Autowired
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return Person.class.equals((aClass));
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
////        Person person = (Person) o;
////        if (personDAO.show(person.getEmail()).isPresent()) {
////            errors.rejectValue("email", "", "This email already taken");
////        }
//    }
//}
