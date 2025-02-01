package com.web.nakshatra.rest;

import com.web.nakshatra.model.ProductCategory;
import com.web.nakshatra.repository.ProductCategoryRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ProductCategory")
public class ProductCategoryResource {

    private final ProductCategoryRepo ProductCategoryRepository;

    public ProductCategoryResource(ProductCategoryRepo ProductCategoryRepository) {
        this.ProductCategoryRepository = ProductCategoryRepository;
    }

    // Create ProductCategory
    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory ProductCategory) {
        return ResponseEntity.ok(ProductCategoryRepository.save(ProductCategory));
    }

    // Get all ProductCategory items
    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllProductCategory() {
        return ResponseEntity.ok(ProductCategoryRepository.findAll());
    }

    // Get ProductCategory by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Integer id) {
        Optional<ProductCategory> ProductCategory = ProductCategoryRepository.findById(id);
        return ProductCategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update ProductCategory
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable Integer id, @RequestBody ProductCategory updatedProductCategory) {
        return ProductCategoryRepository.findById(id)
                .map(existingProductCategory -> {
                    updatedProductCategory.setProductCategoryId(existingProductCategory.getProductCategoryId());
                    return ResponseEntity.ok(ProductCategoryRepository.save(updatedProductCategory));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete ProductCategory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Integer id) {
        if (ProductCategoryRepository.existsById(id)) {
            ProductCategoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}