package com.example.cartservice.dto;

public class UpdateQuantityRequest {

    private Long bookId;
    private int quantity;

    public Long getBookId() { return bookId; }
    public int getQuantity() { return quantity; }
}