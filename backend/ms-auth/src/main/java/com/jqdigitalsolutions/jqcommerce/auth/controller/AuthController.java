package com.jqdigitalsolutions.jqcommerce.auth.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/lo*in")
    public String login() {

       return "Login OK";
    }
}