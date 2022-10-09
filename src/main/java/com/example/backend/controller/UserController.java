package com.example.backend.controller;

import com.example.backend.dto.UserRequest;
import com.example.backend.entity.UserRole;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserRole login(@RequestBody UserRequest request) {
        UserRole u = userService.login(request);
        return u;
    }

}
