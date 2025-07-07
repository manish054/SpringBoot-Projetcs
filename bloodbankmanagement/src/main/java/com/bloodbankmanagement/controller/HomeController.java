package com.bloodbankmanagement.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbankmanagement.model.User;
import com.bloodbankmanagement.service.UserService;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/BloodBankManagementPortal")
    public String home(){
        return "Welcome to Blood Bank Management Portal";
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        return userService.register(user);
    }
}
