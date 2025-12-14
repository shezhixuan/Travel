// ProductPurchaseRepository.java
package org.zjsru.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zjsru.domain.ProductPurchase;

import java.util.List;

public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Long> {
    List<ProductPurchase> findByUserId(Long userId);
}