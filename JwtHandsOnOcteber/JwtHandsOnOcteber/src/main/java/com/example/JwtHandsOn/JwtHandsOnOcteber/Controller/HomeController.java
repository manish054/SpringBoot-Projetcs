package com.example.JwtHandsOn.JwtHandsOnOcteber.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.JwtHandsOn.JwtHandsOnOcteber.model.Users;
import com.example.JwtHandsOn.JwtHandsOnOcteber.repo.UserRepo;
import com.example.JwtHandsOn.JwtHandsOnOcteber.service.JwtService;
import com.example.JwtHandsOn.JwtHandsOnOcteber.service.UserService;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/")
    public String home(){
        return "Welcome Home 8081";
    }

    @GetMapping("/register")
    public Users register(){
     return userService.register(); 
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println("user.getUsername()---"+user.getUsername());
        System.out.println("user.getPassword()---"+user.getPassword());
        Users user1 = userRepo.findByUsername(user.getUsername());
        System.out.println(user1.getUsername()+"---"+user.getPassword());
      Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "loggin Failed";
    }
}
