package com.felxx.credit_evaluator_microservice.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felxx.credit_evaluator_microservice.domain.RequestCardEmissionData;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardEmissionPublisher {
    
    private final RabbitTemplate rabbitTemplate;
    private final Queue cardEmissionQueue;

    public void requestCardEmission(RequestCardEmissionData data) {
        try {
            String message = convertIntoJson(data);
            rabbitTemplate.convertAndSend(cardEmissionQueue.getName(), message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert RequestCardEmissionData to JSON", e);
        }
    }

    private String convertIntoJson(RequestCardEmissionData data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(data);
    }
}
