package com.scm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.scm.userform.AddContactForm;

public interface ContactService {
    ResponseEntity<Object> createContact(AddContactForm contactForm, Authentication authentication);
    ResponseEntity<Object> updateContact(int id, AddContactForm contactForm);
    ResponseEntity<Object> deleteContact(int id);
    ResponseEntity<Object> getContacts(String email, int page, int size, String sortBy, String direction);
    ResponseEntity<Object> getById(int id);
    ResponseEntity<Object> search(String field, String keyword);
    ResponseEntity<Object> getByUserId(int userId);
}
