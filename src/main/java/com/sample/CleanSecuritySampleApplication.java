package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Sample Spring Security App", version = "1.0", description = "A project made by spring boot 2.7.4"))
public class CleanSecuritySampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanSecuritySampleApplication.class, args);
	}

}
