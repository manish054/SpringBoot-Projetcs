package com.scm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

    Optional<User> findByEmailId(String email);
    Optional<User> findByEmailToken(String token);

}