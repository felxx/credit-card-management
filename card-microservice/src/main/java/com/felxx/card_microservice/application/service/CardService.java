package com.felxx.card_microservice.application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felxx.card_microservice.application.domain.Card;
import com.felxx.card_microservice.infra.repository.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
    
    private final CardRepository cardRepository;

    @Transactional
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> findCardIncomeLessThan(Long amount) {
        var incomeBigDecimal = BigDecimal.valueOf(amount);
        return cardRepository.findByIncomeLessThanEqual(incomeBigDecimal).orElseThrow(() -> 
            new RuntimeException("No cards found with income less than " + amount));
    }
}
