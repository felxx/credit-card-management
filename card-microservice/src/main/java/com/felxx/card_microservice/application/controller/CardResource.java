package com.felxx.card_microservice.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felxx.card_microservice.application.domain.Card;
import com.felxx.card_microservice.application.representation.CardCreateRequest;
import com.felxx.card_microservice.application.service.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardResource {

    private final CardService cardService;
    
    @GetMapping
    public String status() {
        return "Card microservice is running!";
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CardCreateRequest request){
        Card card = request.toCard();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
