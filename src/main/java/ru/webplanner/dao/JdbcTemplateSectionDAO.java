package ru.webplanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.webplanner.models.Section;
import ru.webplanner.models.SessionFacade;

import java.sql.Types;
import java.util.List;

@Component
public class JdbcTemplateSectionDAO implements SectionDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SessionFacade sessionFacade;

    @Autowired
    public JdbcTemplateSectionDAO(JdbcTemplate jdbcTemplate, SessionFacade sessionFacade) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionFacade = sessionFacade;
    }

    @Override
    public List<Section> index() {
        int personId = sessionFacade.getPerson().getId();
        return jdbcTemplate.query("SELECT * FROM Section WHERE personId=?",
                new Object[] {personId},
                new int[] {Types.INTEGER},
                new BeanPropertyRowMapper<>(Section.class));
    }

    @Override
    public Section show(int id) {
        int personId = sessionFacade.getPerson().getId();
        return jdbcTemplate.query("SELECT * FROM Section WHERE id=? AND personId=?",
                new Object[] {id, personId},
                new int[] {Types.INTEGER, Types.INTEGER},
                new BeanPropertyRowMapper<>(Section.class))
                .stream().findFirst().orElse(null);
    }

    @Override
    public void save(Section section) {
        int personId = sessionFacade.getPerson().getId();
        jdbcTemplate.update("INSERT INTO Section VALUES (default,?,?)",
                personId, section.getName());
    }

    @Override
    public void update(int id, Section section) {
        int personId = sessionFacade.getPerson().getId();
        jdbcTemplate.update("UPDATE Section SET name=? WHERE id=? AND personId=?",
                section.getName(), id, personId);
    }

    @Override
    public void delete(int id) {
        int personId = sessionFacade.getPerson().getId();
        jdbcTemplate.update("DELETE FROM Section WHERE id=? AND personId=?",
                id, personId);
    }
}
