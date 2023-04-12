package com.cloud.project.controller;

import com.cloud.project.common.ResponseEntity;
import com.cloud.project.entity.User;
import com.cloud.project.service.impl.UserService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {return null;}
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {return null;}
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {return null;}
}
