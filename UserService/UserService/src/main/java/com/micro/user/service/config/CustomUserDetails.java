package com.micro.user.service.config;

import com.micro.user.service.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetails implements UserDetails {


    private User userData=null;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails() {
    }

    public CustomUserDetails(User user) {
        userData=user;
        this.username = user.getName();
        this.password = user.getPassword();
        this.authorities= Stream.of(user.getRoles().split(","))
        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
