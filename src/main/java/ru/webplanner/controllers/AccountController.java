package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webplanner.dao.AccountDAO;
import ru.webplanner.dao.ModuleDAO;
import ru.webplanner.dao.NoteDAO;
import ru.webplanner.dao.SectionDAO;
import ru.webplanner.models.Account;

@Controller
public class AccountController {

    private final AccountDAO accountDAO;
    private final SectionDAO sectionDAO;
    private final ModuleDAO moduleDAO;
    private final NoteDAO noteDAO;

    @Autowired
    public AccountController(AccountDAO accountDAO,
                             SectionDAO sectionDAO,
                             ModuleDAO moduleDAO,
                             NoteDAO noteDAO) {
        this.accountDAO = accountDAO;
        this.sectionDAO = sectionDAO;
        this.moduleDAO = moduleDAO;
        this.noteDAO = noteDAO;
    }

    @GetMapping("/{userName}")
    public String show(@PathVariable("userName") String userName,
                       @RequestParam(value = "tab", required = false) Integer sectionId,
                       @RequestParam(value = "module", required = false) Integer moduleId,
                       Model model) {
        model.addAttribute("account", accountDAO.show(userName));
        model.addAttribute("sections", sectionDAO.index());
        model.addAttribute("sectionView", sectionDAO.show(sectionId));
        model.addAttribute("modules", moduleDAO.index(sectionId));
        model.addAttribute("moduleView", moduleDAO.show(moduleId));
        model.addAttribute("notes", noteDAO.index(moduleId));
        return "account/show";
    }

    @GetMapping("/{userName}/settings")
    public String settings(@PathVariable("userName") String userName,
                           Model model) {
        model.addAttribute("account", accountDAO.show(userName));
        return "account/settings";
    }

    @GetMapping("/new")
    public String newAccount(@ModelAttribute("account") Account account) {
        return "account/new";
    }

    @PostMapping
    public String saveAccount(@ModelAttribute("account") Account account) {
        accountDAO.save(account);
        return "login/login";
    }

    @PatchMapping("/{userName}")
    public String updateAccount(@ModelAttribute("account") Account account,
                                @PathVariable("userName") String userName) {
        accountDAO.update(userName, account);
        return "redirect:" + userName;
    }

    @DeleteMapping("/{userName}")
    public String deleteAccount(@PathVariable("userName") String userName,
                                Model model) {
        accountDAO.delete(userName);
        sectionDAO.delete();
        return "redirect:/login";
    }
}
