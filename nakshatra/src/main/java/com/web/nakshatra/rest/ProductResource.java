package com.web.nakshatra.rest;

import com.web.nakshatra.model.Product;
import com.web.nakshatra.repository.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Product")
public class ProductResource {

    private final ProductRepo ProductRepository;

    public ProductResource(ProductRepo ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    // Create Product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product Product) {
        return ResponseEntity.ok(ProductRepository.save(Product));
    }

    // Get all Product items
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(ProductRepository.findAll());
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> Product = ProductRepository.findById(id);
        return Product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        return ProductRepository.findById(id)
                .map(existingProduct -> {
                    updatedProduct.setProductId(existingProduct.getProductId());
                    return ResponseEntity.ok(ProductRepository.save(updatedProduct));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (ProductRepository.existsById(id)) {
            ProductRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
