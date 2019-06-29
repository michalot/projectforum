package com.sda.demo.controller;

import com.sda.demo.model.UserDto;
import com.sda.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public ModelAndView addUser() {
        return new ModelAndView("registerForm", "userToInsert",
                new UserDto());
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute UserDto userDto) throws ParseException {
        System.out.println(userDto.getLogin());
        userService.saveUser(userDto);

        return "userSaveResult";
    }

}
