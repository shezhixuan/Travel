package org.zjsru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zjsru.domain.Product;
import org.zjsru.service.ProductService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // 获取所有商品
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return productService.searchProducts(search);
        }
        return productService.getAllProducts();
    }

    // 获取单个商品
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 更新商品
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 购买商品
    @PostMapping("/{productId}/purchase")
    public ResponseEntity<?> purchaseProduct(@PathVariable Long productId, @RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        try {
            boolean success = productService.purchaseProduct(productId, userId);
            if (success) {
                return ResponseEntity.ok(Map.of("success", true, "message", "购买成功"));
            } else {
                return ResponseEntity.status(400).body(Map.of("success", false, "message", "购买失败：库存不足或积分不足"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}