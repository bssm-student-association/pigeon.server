package com.project.insert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class InsertApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsertApplication.class, args);
	}

}