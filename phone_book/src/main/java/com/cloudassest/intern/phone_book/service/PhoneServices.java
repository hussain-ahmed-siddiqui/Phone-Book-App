package com.cloudassest.intern.phone_book.service;

import com.cloudassest.intern.phone_book.model.User;
import com.cloudassest.intern.phone_book.model.Contact;
import com.cloudassest.intern.phone_book.repositories.ContactRepository;
import com.cloudassest.intern.phone_book.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneServices {
@Autowired
    ContactRepository contactRepository;
@Autowired
    UserRepository userRepository;
    //for fetching user account
    public User findUser(String phoneNum) {
        User user = userRepository.findByPhoneNum(phoneNum);
        System.out.println(user);
        return user;
    }
    //for fetching the contacts associated with the user that is found by the findByUsername() function
    public List<Contact> findByUser(User currentUser) {
//        Contact contact = new Contact(currentUser.getUserName(),currentUser.getPhoneNum());

        //        contacts.add(contact);
        return contactRepository.findByUser(currentUser);
    }

    public ResponseEntity<?> registerNewUser(String userName,String password, String phoneNum, String email){
        if(userRepository.findByPhoneNum(phoneNum)!=null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: An account with that phone number already exists.");
        }

        User newUser = new User(userName,password,phoneNum);
        if(email!=null) {
            newUser.setEmail(email);
        }
        userRepository.save(newUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/login");
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }

    public String AuthenticateUser(String phoneNum, String password){
        User user = userRepository.findByPhoneNum(phoneNum);
        if(user==null){
            return "redirect:/login.html";
        }
        return "redirect:/createContact.html";
    }

    public ResponseEntity<?> addContact(String firstName, String middleName, String lastName, String phone) {
        Contact contact = new Contact(firstName,phone);
        contact.setName(middleName,lastName);
        contact.setUser(findUser("03363482817"));
        contactRepository.save(contact);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/contacts");
        return new ResponseEntity<>(headers,HttpStatus.FOUND);

    }
}
