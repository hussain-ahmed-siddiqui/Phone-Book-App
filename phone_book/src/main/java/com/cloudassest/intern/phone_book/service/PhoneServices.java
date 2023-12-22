package com.cloudassest.intern.phone_book.service;

import com.cloudassest.intern.phone_book.model.User;
import com.cloudassest.intern.phone_book.model.Contact;
import com.cloudassest.intern.phone_book.repositories.ContactRepository;
import com.cloudassest.intern.phone_book.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.List;
import java.util.Objects;

@Service
public class PhoneServices {
@Autowired
    ContactRepository contactRepository;
@Autowired
    UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public HttpSession currentSession(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(false);
    }
    public User getCurrentUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false); // true == allow create
        String phoneNum = (String) session.getAttribute("phoneNum");

        return findUser(phoneNum);
    }
    //for fetching user account
    public User findUser(String phoneNum) {
        return userRepository.findByPhoneNum(phoneNum);
    }
    //for fetching the contacts associated with the user that is found by the findByUsername() function
    public List<Contact> findByUser(User currentUser) {

        return contactRepository.findByUser(currentUser);
    }

    public ResponseEntity<?> registerNewUser(String userName,String password, String phoneNum, String email){
        if(userRepository.findByPhoneNum(phoneNum)!=null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: An account with that phone number already exists.");
        }
        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User(userName,hashedPassword,phoneNum);
        if(email!=null) {
            newUser.setEmail(email);
        }
        userRepository.save(newUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/login");
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }

    public ResponseEntity<?> AuthenticateUser(String phoneNum, String password){
        User user = userRepository.findByPhoneNum(phoneNum);
        HttpHeaders headers = new HttpHeaders();


        if(!passwordEncoder.matches(password, user.getPassword())){
            headers.add("Location", "/login");
            return new ResponseEntity<>(headers,HttpStatus.FOUND);
        }
        if(user==null){
            headers.add("Location", "/login");
            return new ResponseEntity<>(headers,HttpStatus.FOUND);
        }
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("phoneNum", phoneNum);
        headers.add("Location", "/contacts/list");
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }

    public ResponseEntity<?> addContact(String firstName, String middleName, String lastName, String phone, String email) {
        Contact contact = new Contact(firstName,phone);
        contact.setName(middleName,lastName);
        contact.setEmail(email);
        contact.setUser(getCurrentUser());
        contactRepository.save(contact);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/contacts/list");
        return new ResponseEntity<>(headers,HttpStatus.FOUND);

    }


}
