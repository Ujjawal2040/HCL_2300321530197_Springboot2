package com.hcl.hclassessment2.service;

import com.hcl.hclassessment2.model.CartItem;
import com.hcl.hclassessment2.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartItem addProductToCart(Long customerId, Long productId, Integer quantity) {
        Optional<CartItem> existingItem = cartRepository.findByCustomerIdAndProductId(customerId, productId);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            return cartRepository.save(item);
        } else {
            CartItem newItem = new CartItem(customerId, productId, quantity);
            return cartRepository.save(newItem);
        }
    }

    public List<CartItem> getCartByCustomer(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    public CartItem updateCartQuantity(Long customerId, Long productId, Integer quantity) {
        CartItem item = cartRepository.findByCustomerIdAndProductId(customerId, productId)
                .orElseThrow(() -> new RuntimeException("Product not found in this customer's cart"));

        if (quantity <= 0) {
            cartRepository.delete(item);
            return null;
        }

        item.setQuantity(quantity);
        return cartRepository.save(item);
    }

    public void removeProductFromCart(Long customerId, Long productId) {
        cartRepository.deleteByCustomerIdAndProductId(customerId, productId);
    }

    public void clearCart(Long customerId) {
        cartRepository.deleteByCustomerId(customerId);
    }
}
