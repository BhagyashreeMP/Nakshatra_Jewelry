package com.web.nakshatra.repository;

import com.web.nakshatra.model.Product;
import com.web.nakshatra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
