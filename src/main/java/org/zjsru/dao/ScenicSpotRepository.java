package org.zjsru.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zjsru.domain.ScenicSpot;

import java.util.List;

public interface ScenicSpotRepository extends JpaRepository<ScenicSpot, Long> {
    List<ScenicSpot> findByNameContaining(String name);
}
