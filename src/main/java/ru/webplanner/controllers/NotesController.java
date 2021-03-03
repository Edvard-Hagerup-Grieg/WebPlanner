package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.NoteDAO;
import ru.webplanner.dao.SectionDAO;
import ru.webplanner.models.Note;

@Controller
@RequestMapping("/sections/{sectionId}/modules/{moduleId}/notes")
public class NotesController {

    private final SectionDAO sectionDAO;
    private final NoteDAO noteDAO;

    @Autowired
    public NotesController(SectionDAO sectionDAO, NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
        this.sectionDAO = sectionDAO;
    }

    @GetMapping("/new")
    public String newNote(@PathVariable("sectionId") int sectionId,
                          @PathVariable("moduleId") int moduleId,
                          Note note, Model model) {
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("note", note);
        return "notes/new";

    }

    @PostMapping
    public String saveNote(@ModelAttribute("note") Note note,
                           @PathVariable("moduleId") int moduleId,
                           @PathVariable("sectionId") int sectionId) {
        noteDAO.save(moduleId, note);
        String request = sectionDAO.getOwner() + "?tab=" + sectionId + "&module=" + moduleId;
        return "redirect:/" + request;
    }

    @GetMapping("/{id}/edit")
    public String editNote(@ModelAttribute("note") Note note) {
        return "notes/edit";
    }

    @PatchMapping("/{id}")
    public String updateNote(@ModelAttribute("note") Note note,
                             @PathVariable("id") int id,
                             @PathVariable("sectionId") int sectionId,
                             @PathVariable("moduleId") int moduleId) {
        noteDAO.update(id, note);
        String request = sectionDAO.getOwner() + "?tab=" + sectionId + "&module=" + moduleId;
        return "redirect:/" + request;
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable("id") int id,
                             @PathVariable("sectionId") int sectionId,
                             @PathVariable("moduleId") int moduleId) {
        noteDAO.delete(id);
        String request = sectionDAO.getOwner() + "?tab=" + sectionId + "&module=" + moduleId;
        return "redirect:/" + request;
    }
}
