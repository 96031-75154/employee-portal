package com.emp.main.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	Docket getDocket() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.emp.main"))
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("employee-portal", "SwaggerInterface", "3.2.1", "http://drucare.com",
				new Contact("Prashanth Rapolu", "http://drucare.com", "rapoluprashanth6@gmail.com"), 
				"DC-2023", "http://drucare.com", new ArrayList<>());
	}
}
