package com.cloudassest.intern.phone_book.service;

import com.cloudassest.intern.phone_book.model.User;
import com.cloudassest.intern.phone_book.model.Contact;
import com.cloudassest.intern.phone_book.repositories.ContactRepository;
import com.cloudassest.intern.phone_book.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User findByUsername(String username) {
        User user = new User(username,"03363482817");
        return user;
    }
    //for fetching the contacts associated with the user that is found by the findByUsername() function
    public List<Contact> findByUser(User currentUser) {
        Contact contact = new Contact(currentUser.getUserName(),currentUser.getPhoneNum());
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);
        return contacts;
    }
}
