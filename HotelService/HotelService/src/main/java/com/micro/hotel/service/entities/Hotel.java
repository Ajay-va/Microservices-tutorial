package com.micro.hotel.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Hotel {


    @Id
    private String id;
    private String name;
    private String location;//enums like
    private String about;




}
