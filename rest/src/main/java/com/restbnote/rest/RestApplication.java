package com.restbnote.rest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RestApplication {
	public static void main(String[] args) {
		SpringApplication.run(com.restbnote.rest.RestApplication.class, args);

	}
}
