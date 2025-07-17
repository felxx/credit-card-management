package com.felxx.card_microservice.application.representation;

import java.math.BigDecimal;

import com.felxx.card_microservice.application.domain.ClientCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCardResponse {
    private String name;
    private String brand;
    private BigDecimal cardLimit;

    public static ClientCardResponse from(ClientCard card) {
        return new ClientCardResponse(
            card.getCard().getName(),
            card.getCard().getBrand().toString(),
            card.getCardLimit()
        );
    }
}
