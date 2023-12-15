package com.cloudassest.intern.phone_book.service;

import com.cloudassest.intern.phone_book.model.User;
import com.cloudassest.intern.phone_book.model.Contact;
import com.cloudassest.intern.phone_book.repositories.ContactRepository;
import com.cloudassest.intern.phone_book.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServices {
@Autowired
    ContactRepository contactRepository;
@Autowired
    UserRepository userRepository;

    public User findByUsername(String username) {
        return null;
    }

    public List<Contact> findByUser(User currentUser) {
        return null;
    }
}
