package com.capgeminu.api_ordermaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class ApiOrdermaintenanceserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiOrdermaintenanceserviceApplication.class, args);
	}

}
