package ru.webplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.webplanner.dao.ModuleDAO;

@Controller
public class ModulesController {

    private final ModuleDAO moduleDAO;

    @Autowired
    public ModulesController(ModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
    }
}
