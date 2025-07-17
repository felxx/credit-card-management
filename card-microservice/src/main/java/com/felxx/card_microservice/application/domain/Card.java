package com.felxx.card_microservice.application.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardBrand brand;
    private BigDecimal limit;
    private BigDecimal income;

    public Card(String name, CardBrand brand, BigDecimal limit, BigDecimal income) {
        this.name = name;
        this.brand = brand;
        this.limit = limit;
        this.income = income;
    }
}
