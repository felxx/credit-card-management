package com.felxx.card_microservice.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felxx.card_microservice.application.domain.Card;
import com.felxx.card_microservice.application.domain.ClientCard;
import com.felxx.card_microservice.application.domain.RequestCardEmissionData;
import com.felxx.card_microservice.infra.repository.CardRepository;
import com.felxx.card_microservice.infra.repository.ClientCardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CardEmissionSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.card-emission}")
    public void handleCardEmission(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RequestCardEmissionData requestCardEmissionData = objectMapper.readValue(payload, RequestCardEmissionData.class);
            Card card = cardRepository.findById(requestCardEmissionData.getCardId()).orElseThrow(() -> new RuntimeException("Card not found"));
            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(requestCardEmissionData.getCpf());
            clientCard.setCardLimit(requestCardEmissionData.getCardLimit());

            clientCardRepository.save(clientCard);

        } catch (JsonProcessingException e) {
            log.error("Error processing JSON", e.getMessage());
        }
    }
}
