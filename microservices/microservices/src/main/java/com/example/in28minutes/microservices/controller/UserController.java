package com.example.in28minutes.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.in28minutes.microservices.entity.Users;
import com.example.in28minutes.microservices.services.UserService;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String home() {
        return "Welcome Home";
    }

    @GetMapping("users")
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("users")
    public Users saveUser() {
        return userService.saveUser();
    }

    @GetMapping("users/{id}")
    public EntityModel<Users> getUser(@PathVariable Integer id) {
        Users user = userService.getUser(id);
        // HATEOAS Entity Model to add hyper links for further interactions
        EntityModel<Users> entityModel = EntityModel.of(user);

        // WebMcvLinkBuilder for creating links
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());

        // adding link to entity
        entityModel.add(link.withRel("all-users"));

        /*
         * {
         * "name": "manish",
         * "birthDate": "2025-05-13T16:37:38.542677",
         * "allPosts": [],
         * "userId": 1,
         * "_links": {
         * "all-users": {
         * "href": "http://localhost:8080/users"
         * }
         * }
         * }
         */
        return entityModel;
    }

    @DeleteMapping("user/{id}")
    public String deleteById(@PathVariable Integer id) {
        return userService.deleteById(id);
    }
}
