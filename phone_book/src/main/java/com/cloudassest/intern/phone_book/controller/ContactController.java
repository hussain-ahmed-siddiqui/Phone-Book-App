package com.cloudassest.intern.phone_book.controller;

import com.cloudassest.intern.phone_book.service.PhoneServices;
import org.springframework.stereotype.Controller;

@Controller
public class ContactController {
    private final PhoneServices phoneServices;
    public ContactController(PhoneServices phoneServices){
        this.phoneServices=phoneServices;
    }
}
