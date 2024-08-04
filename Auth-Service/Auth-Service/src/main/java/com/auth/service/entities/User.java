package com.auth.service.entities;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    private String userId;
    private String name;
    private String email;
    private String password;
    private String about;
    private String roles;



}
