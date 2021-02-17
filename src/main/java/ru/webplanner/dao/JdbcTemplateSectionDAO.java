package ru.webplanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.webplanner.models.Section;

import java.sql.Types;
import java.util.List;

@Component
public class JdbcTemplateSectionDAO implements SectionDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateSectionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Section> index(int personId) {
        return jdbcTemplate.query("SELECT * FROM Section WHERE personId=?",
                new Object[] {personId},
                new int[] {Types.INTEGER},
                new BeanPropertyRowMapper<>(Section.class));
    }
}
