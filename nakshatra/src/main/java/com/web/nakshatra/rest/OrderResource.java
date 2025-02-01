package com.web.nakshatra.rest;

import com.web.nakshatra.model.Order;
import com.web.nakshatra.repository.OrderRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Order")
public class OrderResource {

    private final OrderRepo OrderRepository;

    public OrderResource(OrderRepo OrderRepository) {
        this.OrderRepository = OrderRepository;
    }

    // Create Order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order Order) {
        return ResponseEntity.ok(OrderRepository.save(Order));
    }

    // Get all Order items
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder() {
        return ResponseEntity.ok(OrderRepository.findAll());
    }

    // Get Order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Optional<Order> Order = OrderRepository.findById(id);
        return Order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order updatedOrder) {
        return OrderRepository.findById(id)
                .map(existingOrder -> {
                    updatedOrder.setOrderId(existingOrder.getOrderId());
                    return ResponseEntity.ok(OrderRepository.save(updatedOrder));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        if (OrderRepository.existsById(id)) {
            OrderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}