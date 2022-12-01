package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sample.repository.UserRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Sample Spring Security App", version = "1.0", description = "A project made by spring boot 2.7.4"))
@SecurityScheme(name = "Mock", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SecurityScheme(name = "Real", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class CleanSecuritySampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanSecuritySampleApplication.class, args);
	}

}
