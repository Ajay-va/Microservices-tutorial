package com.micro.user.service.externalservice;

import com.micro.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {


    @GetMapping("/rating/getAllRatings")
    public List<Rating> getAllRatingList();

    @PostMapping("/rating/save")
    public Rating saveRating(@RequestBody Rating rating);


}
