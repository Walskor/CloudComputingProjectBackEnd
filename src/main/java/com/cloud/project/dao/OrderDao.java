package com.cloud.project.dao;

import com.cloud.project.entity.Article;
import com.cloud.project.entity.Order;

import java.util.List;

public interface OrderDao {
    Order findById(int id);
    List<Order> findByBuyer(int buyerId);
    int createOrder(Order order);
    int deleteOrder(int id);
}
