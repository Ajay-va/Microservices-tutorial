//package com.micro.user.service.service.impl;
//
//import com.micro.user.service.entities.User;
//import com.micro.user.service.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<User> user= userRepository.findByName(username);
//
//        if(user.isPresent()){
//
//            var userData=user.get();
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(userData.getName())
//                    .password(userData.getPassword())
//                    .roles(getRoles(userData))
//                    .build();
//        }else {
//            throw new UsernameNotFoundException("User not identified...!!!!");
//        }
//    }
//    private String[] getRoles(User userData) {
//        if(userData.getRoles()==null){
//            return new String[]{"USER"};
//        }else {
//            return  userData.getRoles().split(",");
//        }
//    }
//    }
//
