package com.example.orderservice.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @org.jetbrains.annotations.NotNull
    private Long bookId;
    
    @NotNull
    private int quantity;
    
    @NotNull
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}