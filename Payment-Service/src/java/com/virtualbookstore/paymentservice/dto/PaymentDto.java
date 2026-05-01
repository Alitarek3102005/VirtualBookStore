package com.virtualbookstore.paymentservice.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {
	private Long paymentId;
	private String method;
	private String status;
	private double amount;
	private LocalDateTime createdAt;
}
