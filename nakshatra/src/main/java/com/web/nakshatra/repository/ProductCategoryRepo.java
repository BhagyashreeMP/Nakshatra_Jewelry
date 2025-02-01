package com.web.nakshatra.repository;

import com.web.nakshatra.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer> {
}
