package com.auth.service.cofig;

import com.auth.service.entities.UserCredentials;
import com.auth.service.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomDetailService implements UserDetailsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<UserCredentials> user= userCredentialsRepository.findByName(username);

//       if(user.isPresent()){
//           var userData=user.get();
//           return User.builder()
//                   .username(userData.getName())
//                   .password(userData.getPassword())
//                   .roles(getRoles(userData))
//                   .build();
//
//       }else{
//           throw new UsernameNotFoundException("USER NAME NOT AVAILABLE...!!!");
//       }

       return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("USER NAME IS NOT FOUND...!!"+username));

    }

    private String[] getRoles(UserCredentials userData) {

        if(userData.getRoles()==null){
          return new String[]{"USER"};
        }else {
            return userData.getRoles().split(",");
        }

    }
}
