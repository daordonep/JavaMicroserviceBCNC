package com.dharian.pricesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.dharian.pricesapi.infraestructure","com.dharian.pricesapi.application"})
public class PricesapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricesapiApplication.class, args);
	}

}
