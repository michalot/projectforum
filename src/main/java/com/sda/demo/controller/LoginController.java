package com.sda.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/loggedusers"})
    public String logged() {
        return "loggedUsers";
    }
}
