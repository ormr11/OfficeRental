package com.officerental.rental.Controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        // If user is already authenticated, do not redirect back to /login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            // User is logged in, redirect to a home page or something else
            return "redirect:/home";
        }
        return "login"; // else show the login page
    }

    @GetMapping("/home")
    public String showHome(){
        return "welcome";
    }
}
