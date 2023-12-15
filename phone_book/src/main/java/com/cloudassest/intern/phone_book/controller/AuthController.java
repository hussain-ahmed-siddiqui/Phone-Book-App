package com.cloudassest.intern.phone_book.controller;

import com.cloudassest.intern.phone_book.service.PhoneServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
private final PhoneServices phoneServices;

public AuthController(PhoneServices phoneServices){
    this.phoneServices=phoneServices;
}


    @GetMapping("/login")
    public String redirectToLogin() {
        return "redirect:/login.html";
    }

    @PostMapping("/login")
    public String getForm(@RequestParam String name, @RequestParam String password){
        System.out.println(name+" "+password);
        return "redirect:/createContact.html";
    }

}
