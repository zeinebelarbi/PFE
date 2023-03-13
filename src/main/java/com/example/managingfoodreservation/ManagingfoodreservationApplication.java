package com.example.managingfoodreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaAuditing
@EnableWebMvc
@EntityScan
@SpringBootApplication(exclude = FlywayAutoConfiguration.class)
public class ManagingfoodreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagingfoodreservationApplication.class, args);
	}

}
