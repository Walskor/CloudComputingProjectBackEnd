package com.cloud.project.controller;

import com.cloud.project.common.ErrorCode;
import com.cloud.project.common.ResponseEntity;
import com.cloud.project.entity.Article;
import com.cloud.project.entity.Order;
import com.cloud.project.service.ArticleService;
import com.cloud.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.findById(id);
        if (order == null){
            return ResponseEntity.error(ErrorCode.TargetNotExists,"Id invalid",null);
        }
        return ResponseEntity.resultBuild(1, order);
    }

    @GetMapping("/buyer/{id}")
    public ResponseEntity<List<Order>> getOrderByBuyer(@PathVariable Integer id) {
        List<Order> orderList = orderService.findByBuyer(id);
        if (orderList == null){
            return ResponseEntity.error(ErrorCode.TargetNotExists,"No article",null);
        }
        return ResponseEntity.pageBuild(orderList.size(),orderList);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<Article> deleteById(@PathVariable Integer id) {
        int rows = orderService.deleteOrder(id);
        if (rows != 1) {
            return ResponseEntity.error(ErrorCode.DeleteError,"Delete Error",null);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> newOrder(@RequestBody Order order) {
        int affectedRows = orderService.createOrder(order);
        if (affectedRows == 1){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.error(ErrorCode.RegisterFailed, "Create Order Failed!",null);
    }
}
