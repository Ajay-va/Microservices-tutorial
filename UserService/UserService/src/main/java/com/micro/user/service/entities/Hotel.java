package com.micro.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hotel {

    private String id;
    private String name;
    private String location;
    private String about;
}
