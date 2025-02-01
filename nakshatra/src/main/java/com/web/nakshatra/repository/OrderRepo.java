package com.web.nakshatra.repository;

import com.web.nakshatra.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
