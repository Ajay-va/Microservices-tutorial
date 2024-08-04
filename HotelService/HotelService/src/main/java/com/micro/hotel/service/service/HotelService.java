package com.micro.hotel.service.service;

import com.micro.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    public Hotel createHotel(Hotel hotel);

    public List<Hotel> getAllHotels();

    public Hotel getHotel(String hotelId);

}
