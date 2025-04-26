package com.capgemini.garageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class GarageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarageServiceApplication.class, args);
	}

}
