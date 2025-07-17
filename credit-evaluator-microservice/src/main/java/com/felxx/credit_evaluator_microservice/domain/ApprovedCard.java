package com.felxx.credit_evaluator_microservice.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ApprovedCard {
    
    private String name;
    private String brand;
    private BigDecimal cardLimit;
}
