package com.officerental.rental.controllers;

import com.officerental.rental.Services.UserService;
import com.officerental.rental.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Display the registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Refers to register.html (in src/main/resources/templates)
    }

    // Handle form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
         // Default as non-admin
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);


        User result = userService.registerUser(user);
        if (result == null) {
            model.addAttribute("message", "User registration failed!");
            return "register";
        } else {
            model.addAttribute("message", "User registered successfully!");
            return "login"; // Redirect to login page after successful registration
        }
    }
}
