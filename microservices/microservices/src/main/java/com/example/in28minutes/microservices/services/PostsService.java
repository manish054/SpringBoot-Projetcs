package com.example.in28minutes.microservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.in28minutes.microservices.entity.Posts;
import com.example.in28minutes.microservices.entity.Users;
import com.example.in28minutes.microservices.repo.PostsRepo;
import com.example.in28minutes.microservices.repo.UserRepo;

@Service
public class PostsService {

    @Autowired
    PostsRepo postsRepo;

    public Posts createPost(Integer id, Posts post, Users user){
        if(post != null){
            post.setUsers(user);
            return postsRepo.save(post);
        }
        return new Posts();
    }
}
