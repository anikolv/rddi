package com.rddi.registerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RegisterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterAppApplication.class, args);
	}

}
