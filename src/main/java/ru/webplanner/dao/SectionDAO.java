package ru.webplanner.dao;

import ru.webplanner.models.Section;

import java.util.List;

public interface SectionDAO {

    List<Section> index();
    Section show(int id);
    void save(Section section);
    void update(int id, Section section);
    void delete(int id);
}
