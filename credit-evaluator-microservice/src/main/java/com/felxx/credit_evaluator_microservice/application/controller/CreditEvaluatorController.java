package com.felxx.credit_evaluator_microservice.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felxx.credit_evaluator_microservice.application.exception.MicroserviceCommunicationErrorException;
import com.felxx.credit_evaluator_microservice.application.exception.NotFoundException;
import com.felxx.credit_evaluator_microservice.application.exception.RequestCardErrorException;
import com.felxx.credit_evaluator_microservice.application.service.CreditEvaluatorService;
import com.felxx.credit_evaluator_microservice.domain.ClientSituation;
import com.felxx.credit_evaluator_microservice.domain.EvaluationData;
import com.felxx.credit_evaluator_microservice.domain.RequestCardEmissionData;
import com.felxx.credit_evaluator_microservice.domain.RequestCardProtocol;
import com.felxx.credit_evaluator_microservice.domain.ReturnClientEvaluation;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/credit-evaluation")
@RequiredArgsConstructor
public class CreditEvaluatorController {
    
    private final CreditEvaluatorService creditEvaluatorService;

    @GetMapping
    public String status(){
        return "Credit Evaluator Microservice is running";
    }

    @GetMapping(value = "/client-situation", params = "cpf")
    public ResponseEntity<ClientSituation> getClientSituation(@RequestParam("cpf") String cpf) {
        ClientSituation clientSituation;
        try {
            clientSituation = creditEvaluatorService.findClientSituationByCpf(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ReturnClientEvaluation> evaluateClientSituation(@RequestBody EvaluationData data){
        try {
            ReturnClientEvaluation evaluation = creditEvaluatorService.evaluateClientSituation(data.getCpf(), Long.parseLong(data.getIncome()));
            return ResponseEntity.ok(evaluation);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/request-card")
    public ResponseEntity<RequestCardProtocol> requestCard(@RequestBody RequestCardEmissionData data) {
        try {
            RequestCardProtocol protocol = creditEvaluatorService.requestCardEmission(data);
            return ResponseEntity.ok(protocol);
        } catch (RequestCardErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestCardProtocol(e.getMessage()));
        }
    }
}
