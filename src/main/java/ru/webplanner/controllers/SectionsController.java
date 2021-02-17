package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.SectionDAO;
import ru.webplanner.models.Section;

@Controller
@RequestMapping("/sections")
public class SectionsController {

    private final SectionDAO sectionDAO;

    @Autowired
    public SectionsController(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("sections", sectionDAO.index());
        return "sections/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("section", sectionDAO.show(id));
        return "sections/show";
    }

    @GetMapping("/new")
    public String newSection(@ModelAttribute("section") Section section) {
        return "sections/new";
    }

    @PostMapping
    public String saveSection(@ModelAttribute("section") Section section) {
        sectionDAO.save(section);
        return "redirect:/sections";
    }

    @GetMapping("/{id}/edit")
    public String editSection(@ModelAttribute("id") int id, Model model) {
        model.addAttribute(sectionDAO.show(id));
        return "sections/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("section") Section section, @PathVariable("id") int id) {
        sectionDAO.update(id, section);
        return "redirect:/sections";
    }

    @DeleteMapping("/{id}")
    public String deleteSection(@PathVariable("id") int id) {
        sectionDAO.delete(id);
        return "redirect:/sections";
    }
}
