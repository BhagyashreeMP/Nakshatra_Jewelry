package com.web.nakshatra.repository;

import com.web.nakshatra.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {
}
