package ru.webplanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.webplanner.models.Person;

import java.sql.Types;

@Component
public class JdbcTemplatePersonDAO implements PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplatePersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person show(String userName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE userName=?",
                new Object[] {userName},
                new int[] {Types.VARCHAR},
                new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
}
