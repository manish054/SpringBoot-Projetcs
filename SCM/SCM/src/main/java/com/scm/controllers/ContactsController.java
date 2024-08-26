package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.scm.entities.Contact;
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
    public String viewContacts(Model model, Authentication authentication){
        String useremail = Helper.getEmailOfLoggedInUser(authentication);
        ResponseEntity<Object> contacts = contactService.getContacts(useremail);
        model.addAttribute("contacts", contacts.getBody());
        System.out.println("contacts***"+contacts.toString());
        return "user/contacts";
        }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String field, @RequestParam String keyword, Authentication authentication){
        System.out.println("***field***"+field);
        System.out.println("***keyword***"+keyword);
        if(field==null || keyword ==null){
            return "redirect:/user/contacts/search";
        }
        
        ResponseEntity<Object> contacts = contactService.search(field, keyword, authentication);
        System.out.println("****Object****"+field+" --- "+keyword+" *****"+contacts.getBody());
        model.addAttribute("contacts", contacts.getBody());
        return "user/search";
    }

    @RequestMapping(value = "/delete/{id}" )
    public String deleteContact(@PathVariable int id){
        System.out.println("****id****"+id);
        contactService.deleteContact(id);
        return "redirect:/user/contacts";
    }

    @GetMapping("/update/{id}")
    public String updateContact(@PathVariable int id, Model model){
        System.out.println("***id***"+id);
        Contact contact = (Contact) contactService.getById(id).getBody();
        System.out.println("***emailId"+contact.getEmailId());
        AddContactForm form = new AddContactForm();
        form.setName(contact.getName());
        form.setEmail(contact.getEmailId());
        form.setContactNum(contact.getPhNum());
        form.setAbout(contact.getAbout());
        form.setAddress(contact.getAddress());
        form.setFavourite(contact.isFavourite());
        form.setLinkedIn(contact.getLinkedInLink());
        form.setWebsite(contact.getWebsiteLink());
        form.setPicture(contact.getPic());
        model.addAttribute("form", form);
        System.out.println("model***"+model.toString());
        return "user/updatecontact";
    }

    @PostMapping("/update/{id}")
    public String updatingContact(@PathVariable int id,  @Valid @ModelAttribute AddContactForm form){
        System.out.println("update api*****"+id);
        System.out.println("***form***"+form.toString());
        contactService.updateContact(id, form);
        return "redirect:/user/contacts";
    }
    }