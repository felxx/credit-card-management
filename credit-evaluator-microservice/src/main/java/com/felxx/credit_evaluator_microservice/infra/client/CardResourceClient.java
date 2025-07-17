package com.felxx.credit_evaluator_microservice.infra.client;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.felxx.credit_evaluator_microservice.domain.Card;
import com.felxx.credit_evaluator_microservice.domain.ClientCard;

@FeignClient(value = "card-microservice", path = "/cards")
public interface CardResourceClient {
    

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> findAllCardsByClient(@RequestParam(value = "cpf") String cpf);

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> findAllCardsIncomeLessThanEqual(@RequestParam("income") Long income);
}
