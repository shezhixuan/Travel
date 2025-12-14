package org.zjsru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zjsru.domain.ScenicSpot;
import org.zjsru.service.ScenicSpotService;

import java.util.List;

@RestController
@RequestMapping("/api/scenic-spots")
public class ScenicSpotController {
    @Autowired
    private ScenicSpotService scenicSpotService;

    @GetMapping("/recommended")
    public List<ScenicSpot> getRecommendedSpots() {
        return scenicSpotService.getRecommendedSpots();
    }

    @GetMapping("/search")
    public List<ScenicSpot> searchSpots(@RequestParam String name) {
        return scenicSpotService.searchSpots(name);
    }

    // 获取推荐景点（根据用户选择的指标排序）
    @GetMapping("/recommended/metrics")
    public List<ScenicSpot> getRecommendedSpots(@RequestParam List<String> metrics) {
        return scenicSpotService.getRecommendedSpots(metrics);
    }
}
