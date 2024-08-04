package com.micro.user.service.service;

import com.micro.user.service.entities.Hotel;
import com.micro.user.service.entities.User;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface UserService {


    public User saveUser(User user);

    public List<User> findAllUsers();

    public User getById(String userId);

    public void deleteUserId(String userId);

    public User updateUser(User user);

   public Hotel sampleMethod(String hotelId);



}
