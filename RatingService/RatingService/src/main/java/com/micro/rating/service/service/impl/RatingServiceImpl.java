package com.micro.rating.service.service.impl;

import com.micro.rating.service.entities.Hotel;
import com.micro.rating.service.entities.Rating;
import com.micro.rating.service.entities.RatingDTO;
import com.micro.rating.service.entities.User;
import com.micro.rating.service.externalservice.HotelService;
import com.micro.rating.service.externalservice.UserService;
import com.micro.rating.service.repositories.RatingRepository;
import com.micro.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

    @Override
    public Rating create(Rating rating) {
      String ratingId=  UUID.randomUUID().toString();
        rating.setRatingId(ratingId);

     List<Hotel> hotelList=   hotelService.getAllHotels();
        hotelList.stream().filter(hotel -> hotel.getId().equals(rating.getHotelId()))
                .map(hotel -> {
                     rating.setHotelId(hotel.getId());
                    return hotel;
                }).collect(Collectors.toList());

     List<User> userList=   userService.getAllUsers();

     userList.stream().filter(user -> user.userId().equals(rating.getUserId()))
             .map(user -> {
                 rating.setUserId(user.userId());
                 return user;
             }).collect(Collectors.toList());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    public List<RatingDTO> getAllRatingData(String ratingId){
        List<RatingDTO> ratingDTOList=new ArrayList<>();
        RatingDTO ratingDTO=new RatingDTO();
       List<Hotel> hotelList=  hotelService.getAllHotels();
       List<User> userList=  userService.getAllUsers();


      Rating rating= ratingRepository.findByRatingId( ratingId);
      


      hotelList.stream().map(hotel -> {

           Hotel hotelData=new Hotel();
           hotelData.setId(hotel.getId());
           hotelData.setName(hotel.getName());
           hotelData.setAbout(hotel.getAbout());
           hotelData.setLocation(hotel.getLocation());

           return hotel;
       }).collect(Collectors.toList());







        return ratingDTOList;
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
