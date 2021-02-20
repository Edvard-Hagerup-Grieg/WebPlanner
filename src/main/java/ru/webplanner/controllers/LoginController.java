package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.webplanner.dao.AccountDAO;
import ru.webplanner.models.Account;
import ru.webplanner.models.SessionFacade;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AccountDAO accountDAO;
    private final SessionFacade sessionFacade;

    @Autowired
    public LoginController(AccountDAO accountDAO, SessionFacade sessionFacade) {
        this.accountDAO = accountDAO;
        this.sessionFacade = sessionFacade;
    }

    @GetMapping
    public String login(@ModelAttribute("account") Account account) {
        return "login/login";
    }

    @PostMapping
    public String validateAccount(@ModelAttribute("account") Account account) {
        if(accountDAO.contains(account)) {
            sessionFacade.setAccount(accountDAO.show(account.getUserName()));
            return "redirect:" + account.getUserName();
        }
        return "login/error";
    }
}
