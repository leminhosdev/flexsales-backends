package com.example.flexsaless;

import com.example.flexsaless.Entitiy.ExcelFile;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FlexsalessApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(FlexsalessApplication.class, args);

	}

}
