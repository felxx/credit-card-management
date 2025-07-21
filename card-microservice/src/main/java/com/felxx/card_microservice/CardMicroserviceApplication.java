package com.felxx.card_microservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@EnableRabbit
@SpringBootApplication
@Slf4j
public class CardMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardMicroserviceApplication.class, args);
	}
}
