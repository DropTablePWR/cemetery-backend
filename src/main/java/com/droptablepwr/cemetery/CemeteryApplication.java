package com.droptablepwr.cemetery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@SpringBootApplication
public class CemeteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CemeteryApplication.class, args);
	}

	@Bean
	public ValidatorFactory validatorFactory(){
		return Validation.buildDefaultValidatorFactory();
	}
}
