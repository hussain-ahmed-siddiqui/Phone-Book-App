package com.cloudassest.intern.phone_book.controller;

import com.cloudassest.intern.phone_book.service.PhoneServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {
private final PhoneServices phoneServices;

public FrontendController(PhoneServices phoneServices){
    this.phoneServices=phoneServices;
}


    @GetMapping("/error")
    public ResponseEntity<?> error(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/login")
    public String redirectToLogin()
    {   if(phoneServices.currentSession()!=null){
        return "redirect:/error";
    }
        return "redirect:/login.html";
    }



    @GetMapping("/register")
    public String signUp(){
        return "redirect:/signup.html";
    }


    @GetMapping("/contacts/new")
    public String createContact(){
        if(phoneServices.currentSession()==null){
            return "redirect:/login.html";
        }

        return "redirect:/createContact.html";
    }

    @GetMapping("/contacts/list")
    public String contactList(){
        if(phoneServices.currentSession()==null){
            return "redirect:/login.html";
        }
        return "redirect:/contactList.html";
    }
}
