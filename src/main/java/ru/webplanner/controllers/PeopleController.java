package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.PersonDAO;

@Controller
public class PeopleController {

    PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/{userName}")
    public String show(@PathVariable("userName") String userName, Model model) {
        model.addAttribute("person", personDAO.show(userName));
        return "people/show";
    }
}
