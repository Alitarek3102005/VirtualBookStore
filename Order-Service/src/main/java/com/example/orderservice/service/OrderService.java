package com.example.orderservice.service;

import com.example.orderservice.DTO.CartDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.DTO.CartItemDTO;
import com.example.orderservice.feign.OrderInterface;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import com.example.orderservice.feign.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderInterface orderInterface;

    @Autowired
    private CatalogClient catalogClient;

    @Transactional
    public Order createOrderFromCart(Long readerId) {

        CartDTO cart = orderInterface.getCart(readerId);
        List<CartItemDTO> cartItems = cart == null ? List.of() : cart.getCartItems();

        if (cartItems == null || cartItems.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty");
        }

        if (cartItems.stream().anyMatch(item -> item.getPrice() == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart contains an item with missing price");
        }

        cartItems.forEach(item -> {
            Boolean inStock = catalogClient.checkStock(
                    item.getBookId(),
                    (long) item.getQuantity()
            );

            if (!Boolean.TRUE.equals(inStock)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Insufficient stock for book: " + item.getBookId()
                );
            }
        });

        Order order = new Order();
        order.setReaderId(readerId);
        order.setCartId(cart.getId());
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setBookId(item.getBookId());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(item.getPrice());
                    orderItem.setOrder(order);

                    return orderItem;

                }).toList();

        order.setOrderItems(orderItems);

        double total = orderItems.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        order.setTotalPrice(total);

        Order saved = orderRepository.save(order);

        cartItems.forEach(item -> catalogClient.reduceStock(
                item.getBookId(),
                (long) item.getQuantity()
        ));

        orderInterface.clearCart(readerId);

        return saved;
    }
}
