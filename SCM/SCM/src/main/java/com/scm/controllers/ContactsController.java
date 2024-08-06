package com.scm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.serviceimplementation.ConatctServiceImplementation;
import com.scm.userform.AddContactForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactsController {

    @Autowired
    private ConatctServiceImplementation contactService;

    @GetMapping("/add")
    public String addContact(Model model){
        AddContactForm contactForm = new AddContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/addcontact";
    }

    @PostMapping("/add")
    public String addContactProcessing(@Valid @ModelAttribute AddContactForm contactForm, BindingResult result, Authentication authentication,
    HttpSession session){
        if(result.hasErrors()){
            System.out.println("****result.getAllErrors()****"+result.getAllErrors());
            session.setAttribute("message", Message.builder()
            .content("Error !!.. Check fields")
            .type(MessageType.red)
            .build());
            return "redirect:/user/contacts/add";
        }
        System.out.println("****contactform.toString()****"+contactForm.toString());
        contactService.createContact(contactForm, authentication);
        session.setAttribute("message", Message.builder()
            .content("Contact Created")
            .type(MessageType.green)
            .build());
        return "redirect:/user/contacts/add";
    }

    //view contacts
    @GetMapping
    public String viewContacts(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
        @RequestParam(value = "direction", defaultValue = "asc") String direction,
        Model model, Authentication authentication){
        String useremail = Helper.getEmailOfLoggedInUser(authentication);
        ResponseEntity<Object> contacts = contactService.getContacts(useremail, page, size, sortBy, direction);
        model.addAttribute("contacts", contacts.getBody());
        return "user/contacts";
        }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String field, @RequestParam String keyword){
        System.out.println("***field***"+field);
        System.out.println("***keyword***"+keyword);
        if(field==null || keyword ==null){
            return "redirect:/user/contacts/search";
        }
        
        ResponseEntity<Object> contacts = contactService.search(field, keyword);
        System.out.println("****Object****"+field+" --- "+keyword+" *****"+contacts.getBody());
        model.addAttribute("contacts", contacts.getBody());
        return "user/search";
    }
    }