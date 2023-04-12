package com.cloud.project.service.impl;

import com.cloud.project.dao.ArticleDao;
import com.cloud.project.dao.OrderDao;
import com.cloud.project.dao.UserDao;
import com.cloud.project.entity.Article;
import com.cloud.project.entity.Order;
import com.cloud.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private OrderDao orderDao;

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findByBuyer(int buyerId) {
        List<Order> articles = orderDao.findByBuyer(buyerId);
        if (articles.size() == 0){
            return null;
        }
        return articles;
    }

    @Override
    public int createOrder(Order order) {
        int rows = orderDao.createOrder(order);
        if (rows == 1){
            Integer articleId = order.getArticleId();
            int count = order.getCount();
            for(int i = 0; i < count; i ++){
                articleDao.addCount(articleId);
            }
        }
        return rows;
    }

    @Override
    public int deleteOrder(int id) {
        return orderDao.deleteOrder(id);
    }
}
