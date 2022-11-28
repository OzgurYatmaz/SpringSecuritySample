package com.sample.swagger;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

 

@Configuration
//@EnableSwagger2
//@ComponentScan("com.sample")
public class Swagger2Config  {
	
	 private ApiInfo apiInfo() {
	        return new ApiInfo("Spring Security Sample Api",
	                "APIs for MyApp.",
	                "1.0",
	                "Terms of service",
	                new Contact("Ozgur Yatmaz", "http://www.linkedin.com/in/ozguryatmaz", "ozguryatmaz@yandex.com"),
	                "License of API",
	                "API license URL",
	                Collections.emptyList());
	    }

	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.OAS_30)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.sample.controller"))//to remove error controller from ui
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	            //    .paths(Predicates.not(PathSelectors.regex("/error.*")))
	                .build();
	    }
} 