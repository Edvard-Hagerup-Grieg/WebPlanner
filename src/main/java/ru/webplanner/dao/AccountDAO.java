package ru.webplanner.dao;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.webplanner.models.Account;

public interface AccountDAO extends UserDetailsService {

    Account show(String userName);
    void save(Account account);
    void update(String userName, Account account);
    void delete(String userName);

    boolean contains(Account account);

}
