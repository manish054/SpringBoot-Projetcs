package com.bloodbankmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloodbankmanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    User findByUserid(String userid);
    User findByEmail(String email);
    User findByUsername(String username);

}
