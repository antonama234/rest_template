package ru.anton.gorbachev.rest_template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import  ru.anton.gorbachev.rest_template.models.User;
import  ru.anton.gorbachev.rest_template.service.UserServiceImpl;

@Controller
public class RegistrationController {
    private final UserServiceImpl service;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserServiceImpl service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/registration")
    public String regUser(@ModelAttribute ("user") User user) {
        if (!service.isExist(user.getLogin())) {
            System.out.println(user.getPassword());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            service.addUser(user);
        }
        return "redirect:/login";
    }
}
