package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.SectionDAO;
import ru.webplanner.models.Person;
import ru.webplanner.models.Section;

import java.util.List;

@Controller
@SessionAttributes("person")
@RequestMapping("/sections")
public class SectionsController {

    private final SectionDAO sectionDAO;
    private final Person person;

    @Autowired
    public SectionsController(@ModelAttribute("person") Person person, SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
        this.person = person;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("sections", sectionDAO.index(person.getId()));
        return "sections/index";
    }
}
