package com.manish.fullstack_backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manish.fullstack_backend.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
