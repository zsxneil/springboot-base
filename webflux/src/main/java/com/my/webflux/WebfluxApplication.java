package com.my.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebfluxApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WebfluxApplication.class);
		application.setWebApplicationType(WebApplicationType.REACTIVE);
		application.run(args);
	}
}
