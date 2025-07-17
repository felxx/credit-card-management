package com.felxx.credit_evaluator_microservice.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Card {

    private String name;
    private String brand;
    private BigDecimal cardLimit;
}
