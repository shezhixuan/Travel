package org.zjsru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjsru.dao.ScenicSpotRepository;
import org.zjsru.domain.ScenicSpot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScenicSpotService {
    @Autowired
    private ScenicSpotRepository scenicSpotRepository;
    @Autowired
    private ReviewService reviewService;

    public List<ScenicSpot> getRecommendedSpots() {
        return scenicSpotRepository.findAll().subList(0, 6); // 假设返回前6个景点
    }

    public List<ScenicSpot> searchSpots(String name) {
        return scenicSpotRepository.findByNameContaining(name);
    }
    // 获取推荐景点（根据用户选择的指标排序）
    public List<ScenicSpot> getRecommendedSpots(List<String> selectedMetrics) {
        List<ScenicSpot> spots = scenicSpotRepository.findAll().subList(0, 6); // 假设返回前6个景点

        // 计算每个景点的指标得分
        Map<Long, Integer> spotScores = new HashMap<>();
        for (ScenicSpot spot : spots) {
            int score = 0;
            Map<String, String> metrics = reviewService.getReviewMetrics(spot.getId());
            for (String metric : selectedMetrics) {
                if ("高".equals(metrics.get(metric))) {
                    score += 1;
                }
            }
            spotScores.put(spot.getId(), score);
        }

        // 根据得分排序
        return spots.stream()
                .sorted((s1, s2) -> spotScores.get(s2.getId()) - spotScores.get(s1.getId()))
                .collect(Collectors.toList());
    }
}


