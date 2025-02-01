package com.web.nakshatra.rest;

import com.web.nakshatra.model.Cart;
import com.web.nakshatra.repository.CartRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Cart")
public class CartResource {

    private final CartRepo CartRepository;

    public CartResource(CartRepo CartRepository) {
        this.CartRepository = CartRepository;
    }

    // Create Cart
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart Cart) {
        return ResponseEntity.ok(CartRepository.save(Cart));
    }

    // Get all Cart items
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCart() {
        return ResponseEntity.ok(CartRepository.findAll());
    }

    // Get Cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Integer id) {
        Optional<Cart> Cart = CartRepository.findById(id);
        return Cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Cart
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Integer id, @RequestBody Cart updatedCart) {
        return CartRepository.findById(id)
                .map(existingCart -> {
                    updatedCart.setCartId(existingCart.getCartId());
                    return ResponseEntity.ok(CartRepository.save(updatedCart));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Cart
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id) {
        if (CartRepository.existsById(id)) {
            CartRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}