package com.micro.user.service.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FileDTO {



    private String userId;
    private String name;
    private String email;
    private String password;
    private String aboutUser;
    private String roles;

    private String hotelId;
    private String hotelName;
    private String location;
    private String aboutHotel;

    private String ratingId;
    private int rating;
    private String feedback;

}
