package com.micro.hotel.service.service.impl;

import com.micro.hotel.service.entities.Hotel;
import com.micro.hotel.service.exceptions.ResourceNotFoundException;
import com.micro.hotel.service.repositories.HotelRepository;
import com.micro.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        Hotel hotelData=new Hotel();
        if(hotel!=null){
        String hotelId=    UUID.randomUUID().toString();
            hotel.setId(hotelId);
           hotelData =hotelRepository.save(hotel);
        }

        return hotelData;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("ID "+hotelId+"NOT FOUND...!!!"));
    }
}
