package com.cloud.project.service.impl;

import com.cloud.project.entity.User;
import com.cloud.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(Integer id) {return new User();}
    public User getUserByUsernameAndPassword(String username, String password) {return new User();}
    public void createUser(User user) {}
}
