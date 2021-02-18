package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.AccountDAO;

@Controller
public class AccountController {

    private final AccountDAO accountDAO;

    @Autowired
    public AccountController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GetMapping("/{userName}")
    public String show(@PathVariable("userName") String userName, Model model) {
        model.addAttribute("account", accountDAO.show(userName));
        return "account/show";
    }
}
