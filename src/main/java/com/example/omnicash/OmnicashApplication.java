package com.example.omnicash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OmnicashApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmnicashApplication.class, args);
	}

}
