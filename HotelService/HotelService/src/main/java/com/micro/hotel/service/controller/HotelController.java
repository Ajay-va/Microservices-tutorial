package com.micro.hotel.service.controller;

import com.micro.hotel.service.entities.Hotel;
import com.micro.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {


    @Autowired
    private HotelService hotelService;


    @PostMapping("/save")//
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){

       Hotel hotelData= hotelService.createHotel(hotel);

        return ResponseEntity.status(HttpStatus.CREATED).body(hotelData);

    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<Hotel> getHotelId(@PathVariable String hotelId){

        Hotel hotelData= hotelService.getHotel(hotelId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(hotelData);

    }

    @GetMapping("/getAllHotels")//  /hotels
    public ResponseEntity<List<Hotel>> getAllHotels(){

        List<Hotel> hotelData= hotelService.getAllHotels();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(hotelData);

    }







}
