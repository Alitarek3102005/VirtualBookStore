package com.example.orderservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private Long bookId;
    private int quantity;
    private Double price;
}
