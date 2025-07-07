package com.example.in28minutes.microservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.in28minutes.microservices.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

}
