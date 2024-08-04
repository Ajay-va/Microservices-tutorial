package com.micro.user.service.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @Column(name="id")
//    @SequenceGenerator(name="UUID")
//    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
    private String userId;
    private String name;
    private String email;
    private String password;
    private String about;
    private String roles;

    @Transient
    private List<Rating> ratingList=new ArrayList<>();

}
