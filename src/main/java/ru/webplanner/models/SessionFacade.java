package ru.webplanner.models;

import org.springframework.stereotype.Component;

@Component
public class SessionFacade {

    private boolean loggedIn;
    private Person person;

    public SessionFacade() {
    }

    public void setPerson(Person person) {
        this.person = person;
        this.loggedIn = true;
    }

    public Person getPerson() {
        return person;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
