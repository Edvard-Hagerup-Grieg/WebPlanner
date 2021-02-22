package ru.webplanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.webplanner.models.modules.Module;

import java.sql.Types;
import java.util.List;

@Component
public class JdbcTemplateModuleDAO implements ModuleDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateModuleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Module> index(Integer sectionId) {
        return jdbcTemplate.query("SELECT * FROM Module WHERE sectionId=?",
                new Object[] {sectionId},
                new int[] {Types.INTEGER},
                new BeanPropertyRowMapper<>(Module.class));
    }

    @Override
    public Module show(Integer id) {
        return id != null ? jdbcTemplate.query("SELECT * FROM Module WHERE id=?",
                new Object[] {id},
                new int[] {Types.INTEGER},
                new BeanPropertyRowMapper<>(Module.class))
                .stream().findAny().orElse(null) : null;
    }

    @Override
    public void save(int sectionId, Module module) {
        jdbcTemplate.update("INSERT INTO Module VALUES (default,?,?,?)",
                sectionId, "LIST", module.getName());
    }

    @Override
    public void update(int id, Module module) {
        jdbcTemplate.update("UPDATE Module SET type=?, name=? WHERE id=?",
                module.getName(), "LIST", id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Module WHERE id=?",
                id);
    }
}
