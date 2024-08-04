package com.auth.service.controller;

import com.auth.service.entities.User;
import com.auth.service.entities.UserCredentials;
import com.auth.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/register")
    public String addUser(@RequestBody UserCredentials user){

        return authService.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody UserCredentials user){

        Authentication authenticate=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));

        if(authenticate.isAuthenticated()){
            return authService.generateToken(user.getName());
        }else {
            throw new UsernameNotFoundException("USER NOT VALIDATED...!!!!");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token){

        System.out.println("TOKEN VALIDATING STARTED...!!!" );
         authService.validateToken(token);
        return "Token is valid...!!!";
    }

    @GetMapping("/userData")
    public List<User> getAllUserListData(){

        System.out.println("method started...!!!");

     User[] userList=   restTemplate.getForObject("http://USERSERVICE/users/allUsers", User[].class);

//        System.out.println(Arrays.toString(userArray));
       List<User> userListData = Arrays.asList(userList);
        System.out.println(userList);


        return userListData;
    }

    @GetMapping("/sample")
    public String sampleData() {

        System.out.println("Sample Data executed successfully...!!!");

        return "Sample Data will executed....";
    }



}
