package com.felxx.credit_evaluator_microservice.application.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.felxx.credit_evaluator_microservice.domain.ClientCard;
import com.felxx.credit_evaluator_microservice.domain.ClientData;
import com.felxx.credit_evaluator_microservice.domain.ClientSituation;
import com.felxx.credit_evaluator_microservice.infra.client.CardResourceClient;
import com.felxx.credit_evaluator_microservice.infra.client.ClientResourceClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientResourceClient clientResourceClient;
    private final CardResourceClient cardResourceClient;

    public ClientSituation findClientSituationByCpf(String cpf) {
        ResponseEntity<ClientData> clientDataResponse = clientResourceClient.findByCpf(cpf);
        ResponseEntity<List<ClientCard>> clientCardsResponse = cardResourceClient.findAllCardsByClient(cpf);

        return ClientSituation.builder().client(clientDataResponse.getBody()).creditCards(clientCardsResponse.getBody()).build();
    }
}
