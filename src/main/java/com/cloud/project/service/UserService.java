package com.cloud.project.service;

import com.cloud.project.common.ResponseEntity;
import com.cloud.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {
    ResponseEntity<User> register(User user);
    ResponseEntity<User> login(String username, String password);
}

