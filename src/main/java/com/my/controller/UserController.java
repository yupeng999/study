package com.my.controller;


import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController() {
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    String createUser() {
        return userService.createUser("123456");
    }

}
