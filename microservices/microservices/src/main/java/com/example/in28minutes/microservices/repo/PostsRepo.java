package com.example.in28minutes.microservices.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.in28minutes.microservices.entity.Posts;

@Repository
public interface PostsRepo extends JpaRepository<Posts, Integer>{
    
    // List<Posts> findByUserId(Integer userId);
}
