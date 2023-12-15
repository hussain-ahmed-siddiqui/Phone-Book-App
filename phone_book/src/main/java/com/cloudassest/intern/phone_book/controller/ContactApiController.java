package com.cloudassest.intern.phone_book.controller;

import com.cloudassest.intern.phone_book.model.Contact;
import com.cloudassest.intern.phone_book.model.User;
import com.cloudassest.intern.phone_book.service.PhoneServices;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactApiController {

    private final PhoneServices phoneServices;
    public ContactApiController(PhoneServices phoneServices){
        this.phoneServices=phoneServices;
    }



    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        // Retrieve the User entity based on the username
        return phoneServices.findByUsername(username);
    }
    @GetMapping("/contacts")
    public List<Contact> listUserContacts() {
        User currentUser = getCurrentUser(); // Method to get the logged-in user
        return phoneServices.findByUser(currentUser);
    }
}
