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
        int accountId = sessionFacade.getAccount().getId();
        return jdbcTemplate.query("SELECT * FROM Section WHERE accountId=?",
                new Object[] {accountId},
                new int[] {Types.INTEGER},
                new BeanPropertyRowMapper<>(Section.class));
    }

    @Override
    public Section show(int id) {
        int accountId = sessionFacade.getAccount().getId();
        return jdbcTemplate.query("SELECT * FROM Section WHERE id=? AND accountId=?",
                new Object[] {id, accountId},
                new int[] {Types.INTEGER, Types.INTEGER},
                new BeanPropertyRowMapper<>(Section.class))
                .stream().findFirst().orElse(null);
    }

    @Override
    public void save(Section section) {
        int accountId = sessionFacade.getAccount().getId();
        jdbcTemplate.update("INSERT INTO Section VALUES (default,?,?)",
                accountId, section.getName());
    }

    @Override
    public void update(int id, Section section) {
        int accountId = sessionFacade.getAccount().getId();
        jdbcTemplate.update("UPDATE Section SET name=? WHERE id=? AND accountId=?",
                section.getName(), id, accountId);
    }

    @Override
    public void delete(int id) {
        int accountId = sessionFacade.getAccount().getId();
        jdbcTemplate.update("DELETE FROM Section WHERE id=? AND accountId=?",
                id, accountId);
    }
}
