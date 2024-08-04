package com.micro.rating.service.entities;

import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

public record User(String userId,String name,String email,String about) {

}
