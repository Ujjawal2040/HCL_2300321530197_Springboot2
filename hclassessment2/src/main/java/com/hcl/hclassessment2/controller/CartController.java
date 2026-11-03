package com.hcl.hclassessment2.controller;

import com.hcl.hclassessment2.model.CartItem;
import com.hcl.hclassessment2.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // POST: /api/cart/{customerId}/add?productId=1&quantity=2
    @PostMapping("/{customerId}/add")
    public ResponseEntity<CartItem> addProductToCart(
            @PathVariable Long customerId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        CartItem item = cartService.addProductToCart(customerId, productId, quantity);
        return ResponseEntity.ok(item);
    }

    // GET: /api/cart/{customerId}
    @GetMapping("/{customerId}")
    public ResponseEntity<List<CartItem>> viewCustomerCart(@PathVariable Long customerId) {
        List<CartItem> cart = cartService.getCartByCustomer(customerId);
        return ResponseEntity.ok(cart);
    }

    // PUT: /api/cart/{customerId}/update?productId=1&quantity=5
    @PutMapping("/{customerId}/update")
    public ResponseEntity<CartItem> updateCartQuantity(
            @PathVariable Long customerId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        try {
            CartItem updatedItem = cartService.updateCartQuantity(customerId, productId, quantity);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: /api/cart/{customerId}/remove?productId=1
    @DeleteMapping("/{customerId}/remove")
    public ResponseEntity<String> removeProductFromCart(
            @PathVariable Long customerId,
            @RequestParam Long productId) {
        cartService.removeProductFromCart(customerId, productId);
        return ResponseEntity.ok("Product removed from cart successfully.");
    }

    // DELETE: /api/cart/{customerId}/clear
    @DeleteMapping("/{customerId}/clear")
    public ResponseEntity<String> clearEntireCart(@PathVariable Long customerId) {
        cartService.clearCart(customerId);
        return ResponseEntity.ok("Entire cart cleared successfully.");
    }
}
