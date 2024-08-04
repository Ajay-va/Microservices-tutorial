package com.micro.rating.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class RatingServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(RatingServiceApplication.class, args);
		System.out.println("Welcome to RatingService Application....!!!!");
	}

}
