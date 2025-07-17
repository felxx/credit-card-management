package com.felxx.credit_evaluator_microservice.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.felxx.credit_evaluator_microservice.application.exception.MicroserviceCommunicationErrorException;
import com.felxx.credit_evaluator_microservice.application.exception.NotFoundException;
import com.felxx.credit_evaluator_microservice.domain.ApprovedCard;
import com.felxx.credit_evaluator_microservice.domain.Card;
import com.felxx.credit_evaluator_microservice.domain.ClientCard;
import com.felxx.credit_evaluator_microservice.domain.ClientData;
import com.felxx.credit_evaluator_microservice.domain.ClientSituation;
import com.felxx.credit_evaluator_microservice.domain.ReturnClientEvaluation;
import com.felxx.credit_evaluator_microservice.infra.client.CardResourceClient;
import com.felxx.credit_evaluator_microservice.infra.client.ClientResourceClient;

import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientResourceClient clientResourceClient;
    private final CardResourceClient cardResourceClient;

    public ClientSituation findClientSituationByCpf(String cpf) throws NotFoundException, MicroserviceCommunicationErrorException {
        try {
            ResponseEntity<ClientData> clientDataResponse = clientResourceClient.findByCpf(cpf);
            ResponseEntity<List<ClientCard>> clientCardsResponse = cardResourceClient.findAllCardsByClient(cpf);

            return ClientSituation.builder().client(clientDataResponse.getBody()).creditCards(clientCardsResponse.getBody()).build();
        } catch (FeignClientException e) {
            int status = e.status();
            if (status == 404) {
                throw new NotFoundException("Client not found");
            }
            else{
                throw new MicroserviceCommunicationErrorException("Error communicating with microservice");
            }
        }
    }

    public ReturnClientEvaluation evaluateClientSituation(String cpf, Long income) throws NotFoundException, MicroserviceCommunicationErrorException {
        try {
            ResponseEntity<ClientData> clientDataResponse = clientResourceClient.findByCpf(cpf);
            ResponseEntity<List<Card>> clientCardsResponse = cardResourceClient.findAllCardsIncomeLessThanEqual(income);

            List<Card> cards = clientCardsResponse.getBody();
            var approvedCardsList = cards.stream().map(card -> {
                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setName(card.getName());
                approvedCard.setBrand(card.getBrand());
                approvedCard.setCardLimit(card.getCardLimit());
                return approvedCard;
            }).collect(Collectors.toList());

            return new ReturnClientEvaluation(approvedCardsList);
        } catch (FeignClientException e) {
            int status = e.status();
            if (status == 404) {
                throw new NotFoundException("No approved cards found for the client");
            } else {
                throw new MicroserviceCommunicationErrorException("Error communicating with microservice");
            }
        }
    }
}
