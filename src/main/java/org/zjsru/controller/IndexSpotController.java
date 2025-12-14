package org.zjsru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zjsru.domain.IndexSpot;
import org.zjsru.service.IndexSpotService;

import java.util.List;

@RestController
@RequestMapping("/api/index-spots")
public class IndexSpotController {
    @Autowired
    private IndexSpotService indexSpotService;

    @GetMapping("/all")
    public List<IndexSpot> getAllSpots() {
        return indexSpotService.getAllSpots();
    }

    @GetMapping("/by-region")
    public List<IndexSpot> getSpotsByRegion(@RequestParam String region) {
        return indexSpotService.getSpotsByRegion(region);
    }

    @GetMapping("/by-category")
    public List<IndexSpot> getSpotsByCategory(@RequestParam String category) {
        return indexSpotService.getSpotsByCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndexSpot> getSpotById(@PathVariable Long id) {
        IndexSpot spot = indexSpotService.getSpotById(id);
        if (spot != null) {
            return ResponseEntity.ok(spot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<IndexSpot> createSpot(@RequestBody IndexSpot spot) {
        IndexSpot createdSpot = indexSpotService.createSpot(spot);
        return ResponseEntity.ok(createdSpot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IndexSpot> updateSpot(@PathVariable Long id, @RequestBody IndexSpot spot) {
        IndexSpot updatedSpot = indexSpotService.updateSpot(id, spot);
        if (updatedSpot != null) {
            return ResponseEntity.ok(updatedSpot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long id) {
        boolean deleted = indexSpotService.deleteSpot(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}