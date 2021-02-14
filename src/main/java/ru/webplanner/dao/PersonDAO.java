package ru.webplanner.dao;

import ru.webplanner.models.Person;

public interface PersonDAO {
    Person show(String userName);
}
