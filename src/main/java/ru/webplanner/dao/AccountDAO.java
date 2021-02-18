package ru.webplanner.dao;

import ru.webplanner.models.Account;

public interface AccountDAO {

    Account show(String userName);
    boolean contains(Account account);
}
