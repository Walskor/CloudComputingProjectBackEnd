package com.cloud.project.dao;

import com.cloud.project.entity.User;

public interface UserDao {
    User findById(int id);
    User findByUsername(String username);
    int insert(User user);
}
