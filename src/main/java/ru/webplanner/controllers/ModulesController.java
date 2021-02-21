package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.ModuleDAO;
import ru.webplanner.dao.SectionDAO;
import ru.webplanner.models.modules.Module;

@Controller
@RequestMapping("/sections/{sectionId}/modules")
public class ModulesController {

    private final ModuleDAO moduleDAO;
    private final SectionDAO sectionDAO;

    @Autowired
    public ModulesController(ModuleDAO moduleDAO, SectionDAO sectionDAO) {
        this.moduleDAO = moduleDAO;
        this.sectionDAO = sectionDAO;
    }

    @GetMapping("/new")
    public String newModule(@PathVariable("sectionId") int sectionId,
                            Module module,
                            Model model) {
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("module", module);
        return "modules/new";
    }

    @PostMapping
    public String saveModule(@PathVariable("sectionId") int sectionId,
                             @ModelAttribute("module") Module module) {
        moduleDAO.save(sectionId, module);
        return "redirect:/" + sectionDAO.getOwner();
    }

    @GetMapping("/{id}/edit")
    public String editModule(@PathVariable("sectionId") int sectionId,
                             @PathVariable("id") int id,
                             Model model) {
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("module", moduleDAO.show(id));
        return "modules/edit";
    }

    @PatchMapping("/{id}")
    public String updateModule(@ModelAttribute("module") Module module,
                               @PathVariable("id") int id) {
        moduleDAO.update(id, module);
        return "redirect:/" + sectionDAO.getOwner();
    }

    @DeleteMapping("/{id}")
    public String deleteModule(@PathVariable("id") int id) {
        moduleDAO.delete(id);
        return "redirect:/" + sectionDAO.getOwner();
    }
}
