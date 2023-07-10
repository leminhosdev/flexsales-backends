package com.example.flexsaless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FlexsalessApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexsalessApplication.class, args);
	}

}
