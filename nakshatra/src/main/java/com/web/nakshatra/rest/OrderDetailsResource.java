package com.web.nakshatra.rest;

import com.web.nakshatra.model.OrderDetails;
import com.web.nakshatra.repository.OrderDetailsRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/OrderDetails")
public class OrderDetailsResource {

    private final OrderDetailsRepo OrderDetailsRepository;

    public OrderDetailsResource(OrderDetailsRepo OrderDetailsRepository) {
        this.OrderDetailsRepository = OrderDetailsRepository;
    }

    // Create OrderDetails
    @PostMapping
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetails OrderDetails) {
        return ResponseEntity.ok(OrderDetailsRepository.save(OrderDetails));
    }

    // Get all OrderDetails items
    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        return ResponseEntity.ok(OrderDetailsRepository.findAll());
    }

    // Get OrderDetails by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Integer id) {
        Optional<OrderDetails> OrderDetails = OrderDetailsRepository.findById(id);
        return OrderDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update OrderDetails
    @PutMapping("/{id}")
    public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable Integer id, @RequestBody OrderDetails updatedOrderDetails) {
        return OrderDetailsRepository.findById(id)
                .map(existingOrderDetails -> {
                    updatedOrderDetails.setOrderDetailsId(existingOrderDetails.getOrderDetailsId());
                    return ResponseEntity.ok(OrderDetailsRepository.save(updatedOrderDetails));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete OrderDetails
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Integer id) {
        if (OrderDetailsRepository.existsById(id)) {
            OrderDetailsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}