package com.example.cartservice.service;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartItem;
import com.example.cartservice.repository.CartRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addItem(Long userId, Long bookId, int quantity) {
        validateQuantity(quantity);

        Cart cart = getOrCreateCart(userId);

        for (CartItem item : cart.getItems()) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return cartRepository.save(cart);
            }
        }

        CartItem item = new CartItem();
        item.setBookId(bookId);
        item.setQuantity(quantity);
        item.setCart(cart);

        cart.getItems().add(item);

        return cartRepository.save(cart);
    }

    public Cart getCart(Long userId) {
        return getOrCreateCart(userId);
    }

    public Cart removeItem(Long userId, Long bookId) {

        Cart cart = getOrCreateCart(userId);

        cart.getItems().removeIf(item -> item.getBookId().equals(bookId));

        return cartRepository.save(cart);
    }

    public Cart updateQuantity(Long userId, Long bookId, int quantity) {
        validateQuantity(quantity);

        Cart cart = getOrCreateCart(userId);

        for (CartItem item : cart.getItems()) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(quantity);
            }
        }

        return cartRepository.save(cart);
    }

    public void clearCart(Long userId) {
        Cart cart = getOrCreateCart(userId);

        cart.getItems().clear();
        cartRepository.save(cart);
    }

    private Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    try {
                        return cartRepository.saveAndFlush(newCart);
                    } catch (DataIntegrityViolationException exception) {
                        return cartRepository.findByUserId(userId)
                                .orElseThrow(() -> exception);
                    }
                });
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}
