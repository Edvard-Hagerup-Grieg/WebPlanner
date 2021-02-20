package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.SectionDAO;
import ru.webplanner.models.Section;

@Controller
@RequestMapping
public class SectionsController {

    private final SectionDAO sectionDAO;

    @Autowired
    public SectionsController(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @GetMapping("/sections/new")
    public String newSection(@ModelAttribute("section") Section section) {
        return "sections/new";
    }

    @PostMapping("/sections")
    public String saveSection(@ModelAttribute("section") Section section) {
        sectionDAO.save(section);
        return "redirect:/" + sectionDAO.getOwner();
    }

    @GetMapping("/sections/{id}/edit")
    public String editSection(@ModelAttribute("id") int id, Model model) {
        model.addAttribute(sectionDAO.show(id));
        return "sections/edit";
    }

    @PatchMapping("/sections/{id}")
    public String update(@ModelAttribute("section") Section section, @PathVariable("id") int id) {
        sectionDAO.update(id, section);
        return "redirect:/" + sectionDAO.getOwner();
    }

    @DeleteMapping("/sections/{id}")
    public String deleteSection(@PathVariable("id") int id) {
        sectionDAO.delete(id);
        return "redirect:/" + sectionDAO.getOwner();
    }
}
