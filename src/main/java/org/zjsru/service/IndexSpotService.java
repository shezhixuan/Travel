package org.zjsru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjsru.dao.IndexSpotRepository;
import org.zjsru.domain.IndexSpot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IndexSpotService {
    @Autowired
    private IndexSpotRepository indexSpotRepository;

    public List<IndexSpot> getAllSpots() {
        return indexSpotRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<IndexSpot> getSpotsByRegion(String region) {
        return "all".equals(region) ?
                getAllSpots() :
                indexSpotRepository.findByRegion(region);
    }

    public List<IndexSpot> getSpotsByCategory(String category) {
        return "all".equals(category) ?
                getAllSpots() :
                indexSpotRepository.findByCategory(category);
    }

    public IndexSpot getSpotById(Long id) {
        Optional<IndexSpot> spot = indexSpotRepository.findById(id);
        return spot.orElse(null);
    }

    public IndexSpot createSpot(IndexSpot spot) {
        // 设置创建时间为当前时间
        spot.setCreatedAt(LocalDateTime.now());
        return indexSpotRepository.save(spot);
    }

    public IndexSpot updateSpot(Long id, IndexSpot spotDetails) {
        Optional<IndexSpot> spotOptional = indexSpotRepository.findById(id);
        if (spotOptional.isPresent()) {
            IndexSpot spot = spotOptional.get();
            spot.setName(spotDetails.getName());
            spot.setImageUrl(spotDetails.getImageUrl());
            spot.setDescription(spotDetails.getDescription());
            spot.setLocation(spotDetails.getLocation());
            spot.setPrice(spotDetails.getPrice());
            spot.setCategory(spotDetails.getCategory());
            spot.setRegion(spotDetails.getRegion());
            return indexSpotRepository.save(spot);
        }
        return null;
    }

    public boolean deleteSpot(Long id) {
        Optional<IndexSpot> spot = indexSpotRepository.findById(id);
        if (spot.isPresent()) {
            indexSpotRepository.delete(spot.get());
            return true;
        }
        return false;
    }
}