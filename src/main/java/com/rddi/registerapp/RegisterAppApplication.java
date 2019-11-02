package com.rddi.registerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class RegisterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterAppApplication.class, args);
	}
	
	@Bean
	public Docket restHubApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.rddi.registerapp.rest"))
				.build()
				.apiInfo(apiInfo());
	}
	
	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder().title("RestHub API")
	            .description("RestHub REST API Interface")
	            .license("RestHub license")
	            .licenseUrl("/")
	            .version("1.0.0")
	            .build();
	 }
	    

}
