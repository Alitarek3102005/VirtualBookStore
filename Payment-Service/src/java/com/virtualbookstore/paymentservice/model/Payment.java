package com.virtualbookstore.paymentservice.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long paymentId;
    
    @Column(name = "amount")
    private double amount;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "method")
    private String method;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime date;
    
  //  @JoinColumn(name = "order_id", referencedColumnName = "id")
  //  private Order order;
    private Long orderId;


}
