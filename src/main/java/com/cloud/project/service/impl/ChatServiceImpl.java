package com.cloud.project.service.impl;

import com.cloud.project.dao.ArticleDao;
import com.cloud.project.dao.ChatDao;
import com.cloud.project.dao.OrderDao;
import com.cloud.project.dao.UserDao;
import com.cloud.project.entity.Article;
import com.cloud.project.entity.Chat;
import com.cloud.project.entity.User;
import com.cloud.project.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDao chatDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> relatedId(int id) {
        List<Integer> idList = chatDao.relatedId(id);
        int size = idList.size();
        if (size == 0){
            return null;
        }
        List<User> users = new ArrayList<>();
        for(int i = 0; i < size; i++){
            id = idList.get(i);
            User user = userDao.findById(id);
            if (user != null){
                users.add(user);
            }
        }
        if (users.size() == 0){
            return null;
        }
        return users;
    }

    @Override
    public Chat findByChatId(int id) {
        return chatDao.findByChatId(id);
    }

    @Override
    public Chat findByUsers(int a_id, int b_id) {
        return chatDao.findByUsers(a_id, b_id);
    }

    @Override
    public Chat newMessage(int id, int sender, String message) {
        return chatDao.newMessage(id, sender, message);
    }
}
