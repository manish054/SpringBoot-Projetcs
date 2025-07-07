package com.jwtnovember.jwthandson.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtnovember.jwthandson.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    User findByUsername(String username);
}
