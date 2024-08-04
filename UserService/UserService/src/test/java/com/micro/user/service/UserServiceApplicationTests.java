package com.micro.user.service;

import com.micro.user.service.entities.Rating;
import com.micro.user.service.externalservice.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	@Test
	void createRating() {
		Rating rating=Rating.builder().ratingId("").userId("").hotelId("").feedback("There is no data here").rating(0).
				build();
		System.out.println("created data ");
		ratingService.saveRating(rating);
	}



}
