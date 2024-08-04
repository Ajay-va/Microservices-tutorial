package com.micro.rating.service.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@NoArgsConstructor
@AllArgsConstructor
@Data
public  class Hotel {
    private  String id;
    private  String name;
    private  String location;
    private  String about;

}
