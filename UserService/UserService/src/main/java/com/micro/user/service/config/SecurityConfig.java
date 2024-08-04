package com.micro.user.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {




    @Bean
    public UserDetailsService userDetailsService(){
        return  new CustomDetailService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        System.out.println("CSRF TOKEN GENERATED HERE...!!!");

       return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request->{
                    request.requestMatchers("/users/token","/users/register","/users/validate"
                    ,"/hotel/getAllHotels","/rating/getAllRatings","/users/allUsers","/users/**",
                            "/hotel/**","/rating/**").permitAll();
                    request.requestMatchers("/welcome").authenticated();
//                    request.requestMatchers("/hotel/**").hasAuthority("ADMIN");
//                    request.requestMatchers("/users/**","/rating/**").hasAuthority("USER");
                    request.anyRequest().authenticated();})
               .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//               .addFilterBefore(, UsernamePasswordAuthenticationFilter.class)
               .build();








//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(registry->{
//                    registry.requestMatchers(
//                            "/users/register",
//                            "/users/token",
//                            "/users/validate",
//                            "/users/csvExport","/users/allUsers").permitAll();
//                    registry.requestMatchers("/users/**","/rating/**").hasAuthority("USER");
//                    registry.requestMatchers("/hotel/**").hasAuthority("ADMIN");
//                    registry.anyRequest().authenticated();
//                })
//                .build();
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
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }






}
