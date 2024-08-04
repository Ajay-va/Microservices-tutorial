package com.micro.rating.service.externalservice;

import com.micro.rating.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="HOTELSERVICE")
public interface HotelService {

//    @PostMapping("/hotel/save")
//    public Hotel hotelSave(@RequestBody Hotel hotel);

    @GetMapping("/hotel/getAllHotels")
    public List<Hotel> getAllHotels();

//    @GetMapping("/hotel/hotels/{hotelId}")
//    public Hotel getByHotelId(@PathVariable("hotelId") String hotelId);

}
