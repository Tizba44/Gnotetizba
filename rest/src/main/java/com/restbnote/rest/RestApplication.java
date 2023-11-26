package com.restbnote.rest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import com.restbnote.rest.services.JsonFetchService;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.restbnote.rest.RestApplication.class, args);




	}



//	@Bean
//	public CommandLineRunner run(JsonFetchService jsonFetchService) {
//		return args -> {
//			jsonFetchService.fetchDataFromJson();
//		};
//	}



}
