package ru.webplanner.models;

import org.springframework.stereotype.Component;

@Component
public class SessionFacade {

    private boolean loggedIn;
    private Account account;

    public SessionFacade() {
    }

    public void setAccount(Account account) {
        this.account = account;
        this.loggedIn = true;
    }

    public Account getAccount() {
        return account;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
