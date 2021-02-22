package ru.webplanner.dao;

import ru.webplanner.models.Note;

import java.util.List;

public interface NoteDAO {

    List<Note> index(Integer moduleId);
    void save(int moduleId, Note note);
    void update(int id, Note note);
    void delete(int id);
}
