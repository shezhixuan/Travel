package org.zjsru.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zjsru.domain.IndexSpot;

import java.util.List;

public interface IndexSpotRepository extends JpaRepository<IndexSpot, Long> {
    List<IndexSpot> findByRegion(String region);
    List<IndexSpot> findByCategory(String category);
    List<IndexSpot> findAllByOrderByCreatedAtDesc();
}