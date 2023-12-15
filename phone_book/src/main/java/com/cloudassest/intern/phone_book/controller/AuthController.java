package com.cloudassest.intern.phone_book.controller;

import com.cloudassest.intern.phone_book.service.PhoneServices;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {
private final PhoneServices phoneServices;

public AuthController(PhoneServices phoneServices){
    this.phoneServices=phoneServices;
}
}
