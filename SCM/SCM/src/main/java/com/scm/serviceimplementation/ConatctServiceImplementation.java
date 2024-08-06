package com.scm.serviceimplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.helper.Helper;
import com.scm.repository.ContactRepo;
import com.scm.repository.UserRepo;
import com.scm.service.ContactService;
import com.scm.userform.AddContactForm;

@Service
public class ConatctServiceImplementation implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ImageServiceImplementation imageService; 

    @Override
    public ResponseEntity<Object> createContact(AddContactForm contactForm,Authentication authentication) {
        try{
            
            String loggedUserEmail = Helper.getEmailOfLoggedInUser(authentication);
            User user = userRepo.findByEmailId(loggedUserEmail).get();

            String filename = UUID.randomUUID().toString();
            System.out.println("file info : {}"+contactForm.getProfilePic().getOriginalFilename());
            String fileUrl = imageService.uploadImage(contactForm.getProfilePic(), filename);
            Contact contact = new Contact();
            contact.setUser(user);
            contact.setName(contactForm.getName());
            contact.setEmailId(contactForm.getEmail());
            contact.setPhNum(contactForm.getContactNum());
            contact.setAbout(contactForm.getAbout());
            contact.setAddress(contactForm.getAddress());
            contact.setFavourite(contactForm.getFavourite());
            contact.setWebsiteLink(contactForm.getWebsite().length() == 0 ? null : contactForm.getWebsite());
            contact.setLinkedInLink(contactForm.getLinkedIn().length() == 0 ? null : contactForm.getLinkedIn());
            contact.setPic(fileUrl);
            contact.setCloudinaryImagePublicId(filename);
            contactRepo.save(contact);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateContact(int id, AddContactForm contactForm) {
        Optional<Contact> optional = contactRepo.findById(id);
        if(optional.isPresent()){
            Contact contact = optional.get();
            contact.setName(contactForm.getName());
            contact.setEmailId(contactForm.getEmail());
            contact.setPhNum(contactForm.getContactNum());
            contact.setAbout(contactForm.getAbout());
            contact.setAddress(contactForm.getAddress());
            contact.setFavourite(contactForm.getFavourite());
            contact.setWebsiteLink(contactForm.getWebsite().length() == 0 ? null : contactForm.getWebsite());
            contact.setLinkedInLink(contactForm.getLinkedIn().length() == 0 ? null : contactForm.getLinkedIn());
            contact.setPic("https://w7.pngwing.com/pngs/177/551/png-transparent-user-interface-design-computer-icons-default-stephen-salazar-graphy-user-interface-design-computer-wallpaper-sphere-thumbnail.png");
            contactRepo.save(contact);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> deleteContact(int id) {
        Contact contact = contactRepo.findById(id).get();
        if(contact != null){
            contactRepo.delete(contact);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> getContacts(String email, int page, int size, String sortBy, String direction) {
       User user = userRepo.findByEmailId(email).get();
       Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
       Pageable pageable = PageRequest.of(page, size, sort);
       Page<Contact> contacts = contactRepo.findByUser(user,pageable);
       System.out.println("***Contacts***"+contacts.toString());
       return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getById(int id) {
       Optional<Contact> optional = contactRepo.findById(id);
       if(optional.isPresent()){
        return new ResponseEntity<>(optional.get(), HttpStatus.FOUND);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> search(String field, String keyword) {
        List<Contact> contacts = null;
        if(field.equals(AppConstants.SEARCH_BY_EMAIL)){
           contacts = contactRepo.findByEmailIdContaining(keyword);
           return new ResponseEntity<>(contacts, HttpStatus.FOUND);
        }
        if(field.equals(AppConstants.SEARCH_BY_NAME)){
            contacts = contactRepo.findByNameContaining(keyword);
        }
        if(field.equals(AppConstants.SEARCH_BY_CONTACT)){
            contacts = contactRepo.findByPhNumContaining(keyword);
        }

        if(contacts != null){
            return new ResponseEntity<>(contacts, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> getByUserId(int userId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

   

   

}
