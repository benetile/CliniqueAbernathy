package com.frontend.controller;

import com.frontend.model.User;
import com.frontend.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String showUserList(Model model) {
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "addUser";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addUser";
        }
        userRepository.save(user);
        return "redirect:/login";
    }

}
