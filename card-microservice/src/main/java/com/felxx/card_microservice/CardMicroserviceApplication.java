package com.felxx.card_microservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class CardMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardMicroserviceApplication.class, args);
	}
}
