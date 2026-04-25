package com.example.orderservice.service;

import com.example.orderservice.DTO.CartDTO;
import com.example.orderservice.DTO.CartItemDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.feign.OrderInterface;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private OrderInterface orderInterface;

    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

public void createOrderFromCart(Long readerId) {
    CartDTO cart = orderInterface.getCart(readerId);
    List<CartItemDTO> orderItem = cart.getCartItems();

    
    if (orderItem.isEmpty()) {
        throw new RuntimeException("Cart is empty");
    }

    System.out.println(orderItem);
    
//    Order order = new Order();
//    order.setReaderId(readerId);
//    order.setStatus(OrderStatus.PENDING);
//
//    List<OrderItem> orderItems = cartItems.stream()
//        .map(this::convertToOrderItem)
//        .collect(Collectors.toList());
//
//    order.setOrderItems(orderItems);
//    order.setTotalPrice(calculateTotalPrice(orderItems));
//
//    return orderRepository.save(order);
}

//private OrderItem convertToOrderItem(CartItemDTO cartItemDTO) {
//    OrderItem orderItem = new OrderItem();
//    orderItem.setBookId(cartItemDTO.getBookId());
//    orderItem.setQuantity(cartItemDTO.getQuantity());
//    // You might need to fetch price from Book Service
//    orderItem.setPrice(fetchBookPrice(cartItemDTO.getBookId()));
//    return orderItem;
//}

private double calculateTotalPrice(List<OrderItem> orderItems) {
    return orderItems.stream()
        .mapToDouble(item -> item.getPrice() * item.getQuantity())
        .sum();
}
}