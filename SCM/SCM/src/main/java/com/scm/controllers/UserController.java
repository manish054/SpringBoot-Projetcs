package com.scm.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // user dashboard
    @GetMapping("/dashboard")
    public String userDashboard(Principal principal) {
        System.out.println("***principal.getName()***" + principal.getName());
        return "user/dashboard";
    }

    // user profile
    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }

    // user add contact

    // user edit contact

    // user delete contact

    // user view contacts
}
