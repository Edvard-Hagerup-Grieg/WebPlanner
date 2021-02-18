package ru.webplanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.webplanner.models.Account;

import java.sql.Types;

@Component
public class JdbcTemplateAccountDAO implements AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account show(String userName) {
        return jdbcTemplate.query("SELECT * FROM Account WHERE userName=?",
                new Object[] {userName},
                new int[] {Types.VARCHAR},
                new BeanPropertyRowMapper<>(Account.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public boolean contains(Account account) {
        return show(account.getUserName()) != null;
    }
}
