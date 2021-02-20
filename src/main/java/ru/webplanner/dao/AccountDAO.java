package ru.webplanner.dao;

import ru.webplanner.models.Account;

public interface AccountDAO {

    Account show(String userName);
    void save(Account account);
    void update(String userName, Account account);
    void delete(String userName);

    boolean contains(Account account);

}
