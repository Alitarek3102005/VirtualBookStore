package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping({"/api/order", "/order"})
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/item/{readerId}")
    public void getItems(@PathVariable Long readerId) {
        orderService.createOrderFromCart(readerId);
    }

    @PostMapping({"/checkout/{userId}", "/{userId}/checkout"})
    public Order checkout(@PathVariable Long userId) {
        return orderService.createOrderFromCart(userId);
    }
}
