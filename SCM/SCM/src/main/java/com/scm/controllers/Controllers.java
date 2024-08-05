package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.serviceimplementation.UserServiceImplementation;
import com.scm.userform.UserForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class Controllers {

    @Autowired
    private UserServiceImplementation uServiceImplementation;

    @GetMapping({"/","home"})
    public String home() {
        return "home";
    }
    
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "signup";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        
        if(rBindingResult.hasErrors()){
            return "signup";
        }
        uServiceImplementation.saveUser(userForm);
        Message message = Message.builder().content("Account Created !!! Thank You").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/signup";
    }

    @GetMapping("/getAllUsers")
    public String getAllUsers(){
        System.out.println("---uServiceImplementation.getAllUser()---"+uServiceImplementation.getAllUser().getBody()); 
        
        return "redirect:/signup";
    }

    @GetMapping("/delete/{id}")
    public String deletedById(@PathVariable Integer id){
        uServiceImplementation.deleteUser(id);
        return "redirect:/signup";
    }

    // @PostMapping("/authenticate")
    // public String authenticate(@RequestParam String email, @RequestParam String password){
    //     System.out.println("---email---"+email);
    //     System.out.println("---password---"+password);
    //     return "redirect:/user/dashboard";
    // }
}
