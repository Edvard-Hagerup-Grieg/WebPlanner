package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.AccountDAO;
import ru.webplanner.models.Account;

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

    @GetMapping("/new")
    public String newAccount(@ModelAttribute("account") Account account) {
        return "account/new";
    }

    @PostMapping
    public String addAccount(@ModelAttribute("account") Account account) {
        accountDAO.add(account);
        return "login/login";
    }

    @DeleteMapping("/{userName}")
    public String deleteAccount(@PathVariable("userName") String userName, Model model) {
        accountDAO.delete(userName);
        return "redirect:/login";
    }
}
