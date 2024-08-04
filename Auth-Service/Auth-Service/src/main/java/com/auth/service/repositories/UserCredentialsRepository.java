package com.auth.service.repositories;

import com.auth.service.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials,Integer> {

    public Optional<UserCredentials> findByName(String name);
}
