package com.micro.rating.service.externalservice;

import com.micro.rating.service.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="USERSERVICE")
public interface UserService {

    @GetMapping("/users/getAllUsers")
    public List<User> getAllUsers();



}
