package com.cloud.project.dao;

import com.cloud.project.entity.Chat;
import com.cloud.project.entity.Order;

import java.util.List;

public interface ChatDao {
    List<Integer> relatedId(int id);
    Chat findByChatId(int id);
    Chat findByUsers(int a_id, int b_id);
    Chat newMessage(int id, int sender, String message);
}
