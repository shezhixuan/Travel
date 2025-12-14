package org.zjsru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zjsru.domain.Hotel;
import org.zjsru.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            return ResponseEntity.ok(hotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        hotel.setCreatedAt(java.time.LocalDateTime.now());
        Hotel savedHotel = hotelService.saveHotel(hotel);
        return ResponseEntity.ok(savedHotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel existingHotel = hotelService.getHotelById(id);
        if (existingHotel == null) {
            return ResponseEntity.notFound().build();
        }

        // 更新字段
        existingHotel.setName(hotel.getName());
        existingHotel.setImageUrl(hotel.getImageUrl());
        existingHotel.setDescription(hotel.getDescription());
        existingHotel.setLocation(hotel.getLocation());
        existingHotel.setPrice(hotel.getPrice());
        existingHotel.setRating(hotel.getRating());
        existingHotel.setFacilities(hotel.getFacilities());
        existingHotel.setTag(hotel.getTag());
        existingHotel.setRegion(hotel.getRegion());

        Hotel updatedHotel = hotelService.saveHotel(existingHotel);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Hotel> searchHotels(@RequestParam String name) {
        return hotelService.searchHotels(name);
    }

    @GetMapping("/by-region")
    public List<Hotel> getHotelsByRegion(@RequestParam String region) {
        return hotelService.getHotelsByRegion(region);
    }
}