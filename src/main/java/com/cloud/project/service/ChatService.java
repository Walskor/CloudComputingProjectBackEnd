package com.cloud.project.service;

import com.cloud.project.entity.Chat;
import com.cloud.project.entity.User;

import java.util.List;

public interface ChatService {
    List<User> relatedId(int id);
    Chat findByChatId(int id);
    Chat findByUsers(int a_id, int b_id);
    Chat newMessage(int id, int sender, String message);
}
