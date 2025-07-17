package com.felxx.credit_evaluator_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CreditEvaluatorMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditEvaluatorMicroserviceApplication.class, args);
	}

}
