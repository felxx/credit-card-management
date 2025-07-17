package com.felxx.card_microservice.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felxx.card_microservice.application.domain.Card;
import com.felxx.card_microservice.application.domain.ClientCard;
import com.felxx.card_microservice.application.representation.CardCreateRequest;
import com.felxx.card_microservice.application.representation.ClientCardResponse;
import com.felxx.card_microservice.application.service.CardService;
import com.felxx.card_microservice.application.service.ClientCardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardResource {

    private final CardService cardService;
    private final ClientCardService clientCardService;
    
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

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> findAllCardsIncomeLessThanEqual(@RequestParam("income") Long income) {
        List<Card> list = cardService.findCardIncomeLessThanEqual(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClientCardResponse>> findAllCardsByClient(@RequestParam(value = "cpf") String cpf) {
        List<ClientCard> list = clientCardService.findByCpf(cpf);
        List<ClientCardResponse> responseList = list.stream().map(ClientCardResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }
}
