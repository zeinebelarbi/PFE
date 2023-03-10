package com.example.managingfoodreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = FlywayAutoConfiguration.class)
public class ManagingfoodreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagingfoodreservationApplication.class, args);
	}

}
