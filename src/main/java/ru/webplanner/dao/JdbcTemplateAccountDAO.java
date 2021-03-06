package ru.webplanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.webplanner.models.Account;

import java.sql.Types;

@Service
@Component
public class JdbcTemplateAccountDAO implements AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = show(username);

        if(account == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return account;
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
        return show(account.getUsername()) != null;
    }

    @Override
    public void save(Account account) {
        jdbcTemplate.update("INSERT INTO Account VALUES (?,?)",
                account.getUsername(), "password");
    }

    @Override
    public void update(String userName, Account account) {
        jdbcTemplate.update("UPDATE Account SET password=? WHERE userName=?",
                account.getPassword(), userName);
    }

    @Override
    public void delete(String userName) {
        jdbcTemplate.update("DELETE FROM Account WHERE userName=?",
                userName);
    }
}
