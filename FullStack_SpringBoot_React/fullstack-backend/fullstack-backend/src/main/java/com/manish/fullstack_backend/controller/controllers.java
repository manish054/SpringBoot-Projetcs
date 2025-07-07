package com.manish.fullstack_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.manish.fullstack_backend.exception.UserNotFoundException;
import com.manish.fullstack_backend.model.User;
import com.manish.fullstack_backend.repo.UserRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin("http://localhost:3000")
public class controllers {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/")
    public String home(){
        return "Full Stack Project - Back-end - Spring boot";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
       return userRepo.findById(id)
                        .orElseThrow(()->new UserNotFoundException(id));
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        //TODO: process POST request
        return userRepo.save(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepo.findById(id)
                        .map(user -> {
                            user.setName(newUser.getName());
                            user.setUsername(newUser.getUsername());
                            user.setEmail(newUser.getEmail());
                            return userRepo.save(user);
                        }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id){
        User user = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        if(user != null){
            userRepo.deleteById(id);
        }
        return "User with id: "+id+" deleted";
    }
}
