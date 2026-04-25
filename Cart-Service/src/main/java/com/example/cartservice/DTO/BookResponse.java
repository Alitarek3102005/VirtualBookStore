package com.example.cartservice.DTO;

public class BookResponse {

    private Long id;
    private String title;
    private Double price;
    private Long quantity;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Double getPrice() { return price; }
    public Long getQuantity() { return quantity; }
}