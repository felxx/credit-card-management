package com.felxx.credit_evaluator_microservice.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felxx.credit_evaluator_microservice.application.service.CreditEvaluatorService;
import com.felxx.credit_evaluator_microservice.domain.ClientSituation;

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
    public ResponseEntity<ClientSituation> evaluateClientSituation(@RequestParam("cpf") String cpf) {
        ClientSituation clientSituation = creditEvaluatorService.findClientSituationByCpf(cpf);
        return ResponseEntity.ok(clientSituation);
    }
}
