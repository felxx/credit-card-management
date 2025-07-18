package com.felxx.card_microservice.application.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RequestCardEmissionData {
    
    private Long cardId;
    private String cpf;
    private String adress;
    private BigDecimal cardLimit;
}
