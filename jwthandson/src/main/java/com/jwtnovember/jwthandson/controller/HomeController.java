package com.jwtnovember.jwthandson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtnovember.jwthandson.model.User;
import com.jwtnovember.jwthandson.repo.UserRepository;
import com.jwtnovember.jwthandson.service.JwtService;
import com.jwtnovember.jwthandson.service.UserService;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @GetMapping("/")
    public String home(){
        return "Welcome to JWT Hands-on done in November";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println("Login URL---");
        System.out.println("user cred username---"+user.getUsername());
        User loggedUser = userRepo.findByUsername(user.getUsername());
        System.out.println("username---"+loggedUser.getUsername());
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(loggedUser.getUsername());
        }
        return "Login failed !!! Check Credentials again";
    }
}
