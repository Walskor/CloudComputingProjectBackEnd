package com.cloud.project.mapper;


import com.cloud.project.entity.User;

public interface UserMapper {
    User getUserById(Integer id);
    User getUserByUsernameAndPassword(String username, String password);
    void createUser(User user);
}