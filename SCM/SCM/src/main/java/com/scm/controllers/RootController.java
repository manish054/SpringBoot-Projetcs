package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.serviceimplementation.UserServiceImplementation;

@ControllerAdvice
public class RootController {
    @Autowired
    private UserServiceImplementation uServiceImplementation;

    @ModelAttribute
    public void addLoggedUsertoModel(Model model, Authentication authentication){
        if(authentication == null){
            return;
        }
        String email = Helper.getEmailOfLoggedInUser(authentication);
        System.out.println("***email***"+email);
        User user = (User) uServiceImplementation.findByEmail(email).getBody();
        System.out.println("***user.toString()***"+user.toString());
        model.addAttribute("loggedUser", user);
    }
}
