package com.example.JwtHandsOn.JwtHandsOnOcteber.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JwtHandsOn.JwtHandsOnOcteber.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, String>{
    Users findByUsername(String username);

}
