package com.felxx.card_microservice.application.representation;

import java.math.BigDecimal;

import com.felxx.card_microservice.application.domain.Card;
import com.felxx.card_microservice.application.domain.CardBrand;

import lombok.Data;

@Data
public class CardCreateRequest {

    private String name;
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal cardLimit;

    public Card toCard() {
        return new Card(name, brand, cardLimit, income);
    }
}
