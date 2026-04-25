package com.example.orderservice.controller;

import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/item/{readerId}")
    public void getItems(@PathVariable Long readerId){
        orderService.createOrderFromCart(readerId);
    }
}
