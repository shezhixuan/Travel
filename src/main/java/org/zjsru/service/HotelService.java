package org.zjsru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjsru.dao.HotelRepository;
import org.zjsru.domain.Hotel;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public List<Hotel> searchHotels(String name) {
        return hotelRepository.findByNameContaining(name);
    }

    public List<Hotel> getHotelsByRegion(String region) {
        return hotelRepository.findByRegion(region);
    }
}