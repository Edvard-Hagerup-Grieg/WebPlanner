package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.SectionDAO;
import ru.webplanner.models.Person;
import ru.webplanner.models.Section;
import ru.webplanner.models.SessionFacade;

import java.util.List;

@Controller
@RequestMapping("/sections")
public class SectionsController {

    private final SectionDAO sectionDAO;
    private final SessionFacade sessionFacade;

    @Autowired
    public SectionsController(SessionFacade sessionFacade, SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
        this.sessionFacade = sessionFacade;
    }

    @GetMapping
    public String index(Model model) {
        Person person = sessionFacade.getPerson();
        List<Section> sections = sectionDAO.index(person.getId());
        model.addAttribute("sections", sections);
        return "sections/index";
    }
}
