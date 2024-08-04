package com.micro.rating.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RatingDTO<T> {
    List<T> data;
    List<Hotel> hotelList;
    List<Rating> ratingList;
    List<User> userList;


//         private String userId;
//         private String name;
//         private String location;
//         private String about;
//         private String ratingId;
//         private String hotelId;
//         private int rating;
//         private String feedback;


}
