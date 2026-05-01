package com.virtualbookstore.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long amount;
    private Long quantity;
    private String name;
    private String currency;
    private Long orderId;
    
	
}