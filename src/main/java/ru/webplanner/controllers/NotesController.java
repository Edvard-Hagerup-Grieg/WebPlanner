package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.NoteDAO;
import ru.webplanner.models.Note;

@Controller
@RequestMapping("/{userName}?tab={sectionId}&module={moduleId}")
public class NotesController {

    private final NoteDAO noteDAO;

    @Autowired
    public NotesController(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @GetMapping("/new")
    public String newNote(@PathVariable("userName") int userName,
                          @PathVariable("sectionId") int sectionId,
                          @PathVariable("moduleId") int moduleId,
                          Note note, Model model) {
        model.addAttribute("note", note);
        model.addAttribute("url", "/{"+userName+"}?tab={"+sectionId+"}&module={"+moduleId+"}");
        return "notes/new";

    }

    @PostMapping
    public String saveNote(@ModelAttribute("note") Note note,
                           @PathVariable("moduleId") int moduleId) {
        noteDAO.save(moduleId, note);
        return "redirect:";
    }

    @GetMapping("/edit")
    public String editNote(@ModelAttribute("note") Note note) {
        return "notes/edit";
    }

    @PatchMapping("/notes/{id}")
    public String updateNote(@ModelAttribute("note") Note note,
                             @PathVariable("id") int id) {
        noteDAO.update(id, note);
        return "redirect:";
    }

    @DeleteMapping("/notes/{id}")
    public String deleteNote(@PathVariable("id") int id) {
        noteDAO.delete(id);
        return "redirect:";
    }
}
