package ru.anton.gorbachev.rest_template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.anton.gorbachev.rest_template.models.User;
import ru.anton.gorbachev.rest_template.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@RestController
public class AdminController {
    private UserServiceImpl service;

    @Autowired
    public AdminController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/admin")
    public ModelAndView allUsersPage(HttpServletRequest request, Principal principal) {
        User user = service.findUserByLogin(principal.getName());
        HttpSession session = request.getSession();
        session.setAttribute("greeting", "Welcome to your page " + user.getName() + " " + user.getSurname());
        List<User> list = service.allUsers();
        return new ModelAndView("all", "allUsers", list);
    }
}