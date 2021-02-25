package ru.webplanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.webplanner.models.Note;

import java.sql.Types;
import java.util.List;

@Component
public class JdbcTemplateNoteDAO implements NoteDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateNoteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Note> index(Integer moduleId) {
        return moduleId != null ? jdbcTemplate.query("SELECT * FROM Note WHERE moduleId=? ORDER BY dayNumber",
                new Object[] {moduleId},
                new int[] {Types.INTEGER},
                new BeanPropertyRowMapper<>(Note.class)) : null;
    }

    @Override
    public void save(int moduleId, Note note) {
        jdbcTemplate.update("INSERT INTO Note VALUES (default,?,?,?)",
                moduleId, note.getDayNumber(), note.getText());
    }

    @Override
    public void update(int id, Note note) {
        jdbcTemplate.update("UPDATE Note SET dayNumber=?, text=? WHERE id=?",
                note.getDayNumber(), note.getText(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Note WHERE id=?",
                id);
    }
}
