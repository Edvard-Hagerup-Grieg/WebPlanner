package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.webplanner.dao.PersonDAO;
import ru.webplanner.models.Person;
import ru.webplanner.models.SessionFacade;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final PersonDAO personDAO;
    private final SessionFacade sessionFacade;

    @Autowired
    public LoginController(PersonDAO personDAO, SessionFacade sessionFacade) {
        this.personDAO = personDAO;
        this.sessionFacade = sessionFacade;
    }

    @GetMapping
    public String login(@ModelAttribute("person") Person person) {
        return "login/login";
    }

    @PostMapping
    public String validatePerson(@ModelAttribute("person") Person person) {
        if(personDAO.contains(person)) {
            sessionFacade.setPerson(personDAO.show(person.getUserName()));
            return "redirect:/sections";
        }
        return "login/error";
    }
}
