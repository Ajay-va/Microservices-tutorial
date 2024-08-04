package com.micro.user.service.externalservice;

import com.micro.user.service.entities.Hotel;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTELSERVICE")
//@Service
public interface HotelService {

    @GetMapping("/hotel/hotels/{hotelId}")
    public Hotel getHotelId(@PathVariable("hotelId") String hotelId);



}
