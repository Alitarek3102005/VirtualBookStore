package com.example.cartservice.controller;

import com.example.cartservice.DTO.CartRequest;
import com.example.cartservice.DTO.UpdateQuantityRequest;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.service.CartService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{userId}/add")
    public Cart addItem(
            @PathVariable Long userId,
            @RequestBody CartRequest request
    ) {
        return cartService.addItem(userId, request.getBookId(), request.getQuantity());
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @DeleteMapping("/{userId}/remove/{bookId}")
    public Cart removeItem(
            @PathVariable Long userId,
            @PathVariable Long bookId
    ) {
        return cartService.removeItem(userId, bookId);
    }

    @PutMapping("/{userId}/update")
    public Cart updateQuantity(
            @PathVariable Long userId,
            @RequestBody UpdateQuantityRequest request
    ) {
        return cartService.updateQuantity(
                userId,
                request.getBookId(),
                request.getQuantity()
        );
    }

    @DeleteMapping("/{userId}/clear")
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared";
    }
}