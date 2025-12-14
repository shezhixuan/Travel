package org.zjsru.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zjsru.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
}