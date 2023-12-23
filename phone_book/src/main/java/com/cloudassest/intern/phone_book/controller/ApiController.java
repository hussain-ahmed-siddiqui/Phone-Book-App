package com.cloudassest.intern.phone_book.controller;

import com.cloudassest.intern.phone_book.model.Contact;
import com.cloudassest.intern.phone_book.model.User;
import com.cloudassest.intern.phone_book.service.PhoneServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    private final PhoneServices phoneServices;
    public ApiController(PhoneServices phoneServices){
        this.phoneServices=phoneServices;
    }




    @GetMapping("/contacts")
    public List<Contact> listUserContacts(HttpServletRequest request) {

        return phoneServices.findByUser(phoneServices.getCurrentUser());
    }

    @PostMapping("/contacts/new")
    public ResponseEntity<?> newContact(@RequestParam String first_name, @RequestParam String middle_name, @RequestParam String last_name, @RequestParam String email, @RequestParam String phone){

        return phoneServices.addContact(first_name,middle_name,last_name,phone,email);
    }
    @PostMapping("/register")
    public ResponseEntity<?> signDownxD(@RequestParam String name,@RequestParam String password, @RequestParam String phoneNum, @RequestParam String email){

        return phoneServices.registerNewUser(name,password,phoneNum,email);
    }

    @PostMapping("/login")
    public ResponseEntity<?> getForm(@RequestParam String phoneNum, @RequestParam String password){
//        System.out.println(name+" "+password);

        return phoneServices.AuthenticateUser(phoneNum,password);
    }

    @PutMapping("/contacts/edit")
    public void saveEdits(@RequestParam String _id,@RequestParam String name, @RequestParam String phoneNum, @RequestParam String email){

        phoneServices.updateContact(_id,name,phoneNum,email);
    }

    @GetMapping("/performLogout")
    public void logout(){
        HttpSession currSession = phoneServices.currentSession();
        currSession.invalidate();

        currSession = phoneServices.currentSession();
        if(currSession==null){
            System.out.println("*********************************");
        }
    }
}
