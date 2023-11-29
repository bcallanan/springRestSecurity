package com.bcallanan.security.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("path for the package path") // if outside the path // optional
public class RestApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);

	}
}
