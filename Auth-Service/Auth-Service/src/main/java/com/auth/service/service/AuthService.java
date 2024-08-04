package com.auth.service.service;

import com.auth.service.entities.UserCredentials;
import com.auth.service.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthService {


    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public String saveUser(UserCredentials userCredentials){

        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));

        userCredentialsRepository.save(userCredentials);


        return "User added...!!!";
    }


    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    public void validateToken(String token){
         jwtService.validateToken(token);
    }


}
