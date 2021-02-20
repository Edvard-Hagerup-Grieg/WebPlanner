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
        return jdbcTemplate.query("SELECT * FROM Section WHERE ownerAccount=?",
                new Object[] {getOwner()},
                new int[] {Types.VARCHAR},
                new BeanPropertyRowMapper<>(Section.class));
    }

    @Override
    public Section show(Integer id) {
        return id != null ? jdbcTemplate.query("SELECT * FROM Section WHERE id=? AND ownerAccount=?",
                new Object[] {id, getOwner()},
                new int[] {Types.INTEGER, Types.VARCHAR},
                new BeanPropertyRowMapper<>(Section.class))
                .stream().findFirst().orElse(null) : null;
    }

    @Override
    public void save(Section section) {
        jdbcTemplate.update("INSERT INTO Section VALUES (default,?,?)",
                getOwner(), section.getName());
    }

    @Override
    public void update(int id, Section section) {
        jdbcTemplate.update("UPDATE Section SET name=? WHERE id=? AND ownerAccount=?",
                section.getName(), id, getOwner());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Section WHERE id=? AND ownerAccount=?",
                id, getOwner());
    }

    @Override
    public String getOwner() {
        return sessionFacade.getAccount().getUserName();
    }
}
