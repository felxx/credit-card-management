package com.felxx.card_microservice.application.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Card {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardBrand brand;
    private BigDecimal cardLimit;
    private BigDecimal income;

    public Card(String name, CardBrand brand, BigDecimal cardLimit, BigDecimal income) {
        this.name = name;
        this.brand = brand;
        this.cardLimit = cardLimit;
        this.income = income;
    }
}
