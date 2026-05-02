package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/history/{id}")
    public List<Order> getOrders(@PathVariable Long id) {
        return orderService.getAllOrdersByUserId(id);
    }
    @GetMapping("/{userId}/{orderId}")
    public Order getOrder(@PathVariable Long orderId, @PathVariable Long userId) {
        return orderService.getOrderById(orderId, userId);
    }
    @GetMapping
    public List<Order> getAllOrders(){
        return  orderService.getAllOrders();
    }
    @PutMapping("/{id}/updateStatus")
    public Order updateStatus(@PathVariable Long id, @RequestParam OrderStatus orderStatus) {
        return orderService.updateOrderStatus(id, orderStatus);
    }
}

