package com.scm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.Contact;
import com.scm.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer> {

    List<Contact> findByUser(User user);

    List<Contact> findByUserAndNameContainingIgnoreCase(User user, String name);
    List<Contact> findByUserAndEmailIdContainingIgnoreCase(User user, String emailId);
    List<Contact> findByUserAndPhNumContainingIgnoreCase(User user, String phNum);

    // @Query("SELECT c FROM Contact c WHERE c.user.id :=userId")
    // List<Contact> findByUserId(@Param("userId") int userId);

    List<Contact> findByNameOrEmailIdOrPhNum(String name, String email, String phNum);
}
