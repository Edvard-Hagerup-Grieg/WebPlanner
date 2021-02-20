package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.webplanner.dao.NoteDAO;

@Controller
public class NoteController {

    private final NoteDAO noteDAO;

    @Autowired
    public NoteController(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }
}
