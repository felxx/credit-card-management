package com.felxx.credit_evaluator_microservice.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClientCard {
    private String name;
    private String brand;
    private BigDecimal limit;
}
