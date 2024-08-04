package com.micro.user.service.repositories;

import com.micro.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {


    public Optional<User> findByName(String username);





}
