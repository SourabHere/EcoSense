package com.ecosense.BiodiversityService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BiodiversityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiodiversityServiceApplication.class, args);
	}

}
