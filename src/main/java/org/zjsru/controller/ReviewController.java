package org.zjsru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zjsru.domain.Review;
import org.zjsru.domain.ReviewRequest;
import org.zjsru.domain.SpotReviewGroup;
import org.zjsru.service.ReviewService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getReviewsBySpotId(@RequestParam Long spotId) {
        return reviewService.getReviewsBySpotId(spotId);
    }

    @PostMapping
    public Map<String, Object> createReview(@RequestBody ReviewRequest request) {
        return reviewService.createReview(
                request.getSpotId(),
                request.getSpotId(),
                request.getUserName(),// 使用传入的用户名
                request.getContent(),
                request.getRating()
        );
    }
    @GetMapping("/user")
    public List<Review> getReviewsByUserId(@RequestParam Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }
    @GetMapping("/metrics")
    public Map<String, String> getReviewMetrics(@RequestParam Long spotId) {
        return reviewService.getReviewMetrics(spotId);
    }
    // 获取按景点分组的评价
    @GetMapping("/grouped-by-spot")
    public ResponseEntity<List<SpotReviewGroup>> getReviewsGroupedBySpot(
            @RequestParam(required = false) String search) {

        List<SpotReviewGroup> groupedReviews;
        if (search != null && !search.isEmpty()) {
            groupedReviews = reviewService.getReviewsGroupedBySpot(search);
        } else {
            groupedReviews = reviewService.getReviewsGroupedBySpot();
        }

        return ResponseEntity.ok(groupedReviews);
    }

    // 删除评价
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok(Map.of("success", true, "message", "评价删除成功"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("success", false, "message", "删除评价失败: " + e.getMessage()));
        }
    }

}
