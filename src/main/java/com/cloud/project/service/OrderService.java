package com.cloud.project.service;

import com.cloud.project.entity.Order;

import java.util.List;

public interface OrderService {
    Order findById(int id);
    List<Order> findByBuyer(int buyerId);
    int createOrder(Order order);
    int deleteOrder(int id);
}
