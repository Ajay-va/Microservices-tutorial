package com.micro.rating.service.repositories;

import com.micro.rating.service.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository  extends JpaRepository<Rating,String> {


    public List<Rating> findByUserId(String userId);

    public List<Rating> findByHotelId(String hotelId);

    public Rating findByRatingId(String ratingId);


}
