package com.micro.user.service.config;

import com.micro.user.service.entities.User;
import com.micro.user.service.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER= LoggerFactory.getLogger(CustomDetailService.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user= userRepository.findByName(username);
        LOGGER.info("user deatails from "+LOGGER.getName()+"{}",user.get().toString());

        if(user.isEmpty())
            throw new UsernameNotFoundException("User with email: " +user +" not found !");
        else {
        User userData = user.get();
        return new org.springframework.security.core.userdetails.User(
                userData.getName(),
                userData.getPassword(),
                Arrays.stream(userData.getRoles().split(",")).toList()
                        .stream()
                        .map(role-> new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList())
        );



//
//        if(user.isPresent()){
//            var userData=user.get();
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(userData.getName())
//                    .password(userData.getPassword())
//                    .roles(getRoles(userData))
//                    .build();
//
//        }else{
//            LOGGER.info("USER NAME NOT AVAILABLE.."+LOGGER.getName()+"{}",user.get().toString());
//
//            throw new UsernameNotFoundException("USER NAME NOT AVAILABLE...!!!");
//        }






//       return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("USER NAME IS NOT FOUND...!!"+username));

    }



//    private String[] getRoles(User userData) {
//
//        if(userData.getRoles()==null){
//            return new String[]{"USER"};
//        }else {
//            return userData.getRoles().split(",");
//        }
//
    }
}
