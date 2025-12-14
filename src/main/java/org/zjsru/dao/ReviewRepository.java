package org.zjsru.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zjsru.domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findBySpotId(Long spotId);
    List<Review> findByUserId(Long userId);
    @Query("SELECT r FROM Review r LEFT JOIN ScenicSpot s ON r.spotId = s.id WHERE r.userId = :userId")
    List<Review> findByUserIdWithSpotName(@Param("userId") Long userId);
    // 添加按景点名称模糊查询的方法（不区分大小写）
    List<Review> findBySpotNameContainingIgnoreCase(String spotName);

}
