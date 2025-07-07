package com.example.in28minutes.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.in28minutes.microservices.entity.Posts;
import com.example.in28minutes.microservices.entity.Users;
import com.example.in28minutes.microservices.repo.UserRepo;
import com.example.in28minutes.microservices.services.PostsService;

@RestController
@RequestMapping("/users/{id}")
public class PostController {

    @Autowired
    PostsService postsService;

     @Autowired
    UserRepo userRepo;

    @GetMapping("/posts")
    public List<Posts> getAllPosts(@PathVariable Integer id){
        Users user = userRepo.findById(id).get();
        return user.getAllPosts();
    }

    @PostMapping("/posts")
    public Posts createPost(@PathVariable Integer id, @RequestBody Posts post){
         Users user = userRepo.findById(id).get();
        return postsService.createPost(id, post, user);
    }
}
