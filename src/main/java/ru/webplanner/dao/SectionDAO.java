package ru.webplanner.dao;

import ru.webplanner.models.Section;

import java.util.List;

public interface SectionDAO {
    List<Section> index(int personId);
}
