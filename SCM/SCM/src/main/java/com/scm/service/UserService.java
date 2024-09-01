package com.scm.service;

import org.springframework.http.ResponseEntity;

import com.scm.entities.User;
import com.scm.userform.UserForm;

public interface UserService{
    ResponseEntity<Object> saveUser(UserForm userForm);

    ResponseEntity<Object> getUserById(Integer id);

    ResponseEntity<Object> updateUser(User user);

    ResponseEntity<Object> deleteUser(Integer id);

    ResponseEntity<Object> isUserExist(Integer id);

    ResponseEntity<Object> isUserExistByEmail(String email);

    ResponseEntity<Object> getAllUser();

    ResponseEntity<Object> findByEmail(String email);

    ResponseEntity<Object> findByEmailToken(String token);
}