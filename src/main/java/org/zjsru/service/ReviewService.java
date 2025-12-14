package org.zjsru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjsru.dao.ReviewRepository;
import org.zjsru.dao.ScenicSpotRepository;
import org.zjsru.domain.Review;
import org.zjsru.domain.ScenicSpot;
import org.zjsru.domain.SpotReviewGroup;
import org.zjsru.domain.TextProcessor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ScenicSpotRepository scenicSpotRepository;

    public List<Review> getReviewsBySpotId(Long spotId) {
        return reviewRepository.findBySpotId(spotId);
    }

    public Review createReview(Long spotId, String userName, String content) {
        Review review = new Review();
        review.setSpotId(spotId);
        review.setUserName(userName);
        review.setContent(content);
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
    // 创建评价
    public Map<String, Object> createReview(Long spotId, Long userId, String userName, String content, int rating) {
        // 获取景点信息
        ScenicSpot spot = scenicSpotRepository.findById(spotId)
                .orElseThrow(() -> new RuntimeException("景点不存在"));
        Review review = new Review();
        review.setSpotId(spotId);
        review.setUserId(userId);
        review.setUserName(userName);
        review.setContent(content);
        review.setRating(rating);
        review.setSpotName(spot.getName()); // 设置景点名称
        review.setCreatedAt(LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);

        // 计算积分
        int points = 0;
        int wordCount = content.length();
        if (wordCount < 100) points = 1;
        else if (wordCount < 200) points = 2;
        else points = 3;

        // 调用用户服务添加积分
        userService.addPoints(userId, points);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("points", points);
        return response;
    }
    // 获取景点的评价指标
    public Map<String, String> getReviewMetrics(Long spotId) {
        List<Review> reviews = reviewRepository.findBySpotId(spotId);
        Map<String, Integer> metrics = new HashMap<>();
        metrics.put("环境", 0);
        metrics.put("服务", 0);
        metrics.put("娱乐性", 0);
        metrics.put("周边服务", 0);

        for (Review review : reviews) {
            String content = review.getContent();
            String sentiment = TextProcessor.sentimentAnalysis(content);

            // 根据情感分析结果调整指标得分
            int score = "正面".equals(sentiment) ? 1 : -1;

            // 提取关键词并更新指标得分
            List<String> keywords = TextProcessor.extractKeywords(content, 10);
            for (String keyword : keywords) {
                if (keyword.contains("环境") || keyword.contains("干净") || keyword.contains("舒适")) {
                    metrics.put("环境", metrics.get("环境") + score);
                }
                if (keyword.contains("服务") || keyword.contains("态度") || keyword.contains("周到")) {
                    metrics.put("服务", metrics.get("服务") + score);
                }
                if (keyword.contains("娱乐") || keyword.contains("有趣") || keyword.contains("丰富")) {
                    metrics.put("娱乐性", metrics.get("娱乐性") + score);
                }
                if (keyword.contains("交通") || keyword.contains("便利") || keyword.contains("设施")) {
                    metrics.put("周边服务", metrics.get("周边服务") + score);
                }
            }
        }

        // 计算每个指标的情感倾向
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : metrics.entrySet()) {
            if (entry.getValue() > 0) {
                result.put(entry.getKey(), "高");
            } else if (entry.getValue() < 0) {
                result.put(entry.getKey(), "低");
            } else {
                result.put(entry.getKey(), "无数据");
            }
        }

        return result;
    }
    public List<SpotReviewGroup> getReviewsGroupedBySpot() {
        return getReviewsGroupedBySpot(null);
    }

    public List<SpotReviewGroup> getReviewsGroupedBySpot(String searchKeyword) {
        List<Review> reviews;
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            reviews = reviewRepository.findBySpotNameContainingIgnoreCase(searchKeyword);
        } else {
            reviews = reviewRepository.findAll();
        }

        // 按景点分组
        Map<Long, SpotReviewGroup> groups = new HashMap<>();

        for (Review review : reviews) {
            groups.computeIfAbsent(review.getSpotId(), k -> {
                SpotReviewGroup group = new SpotReviewGroup();
                group.setSpotId(review.getSpotId());
                group.setSpotName(review.getSpotName());
                // 这里可以添加获取景点位置信息的逻辑
                group.setLocation("未知位置"); // 实际应从景点服务获取
                group.setReviews(new ArrayList<>());
                return group;
            }).getReviews().add(review);
        }

        return new ArrayList<>(groups.values());
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}
