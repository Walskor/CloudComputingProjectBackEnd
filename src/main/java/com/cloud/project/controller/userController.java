package com.cloud.project.controller;

import com.cloud.project.common.ResponseEntity;
import com.cloud.project.entity.User;
import com.cloud.project.service.UserService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
//        return ResponseEntity.pageBuild(1, false, false,userService.)
//    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return userService.login(user.getUsername(),user.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return userService.register(user);
    }
}
