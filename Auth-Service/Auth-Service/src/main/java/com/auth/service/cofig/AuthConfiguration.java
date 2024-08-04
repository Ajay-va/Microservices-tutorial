package com.auth.service.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Bean
    public UserDetailsService userDetailsService(){
        return  new CustomDetailService();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry->{
                    registry.requestMatchers("/auth/register", "/auth/token",
                    "/auth/validate","/auth/userData","/auth/sample").permitAll();
                    registry.requestMatchers("/users/**").hasRole("USER");
                    registry.requestMatchers("/hotel/**").hasRole("ADMIN");
                    registry.requestMatchers("/rating/**").hasRole("USER");
                })
                .build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService());
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }



}
