package ru.webplanner.dao;

import ru.webplanner.models.modules.Module;

import java.util.List;

public interface ModuleDAO {

    List<Module> index(Integer sectionId);
    Module show(int id);
    void save(int sectionId, Module module);
    void update(int id, Module module);
    void delete(int id);

}
