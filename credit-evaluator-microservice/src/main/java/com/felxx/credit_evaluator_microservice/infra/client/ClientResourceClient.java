package com.felxx.credit_evaluator_microservice.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.felxx.credit_evaluator_microservice.domain.ClientData;

@FeignClient(value = "client-microservice", path = "/clients")
public interface ClientResourceClient {
    
    @GetMapping(params = "cpf")
    public ResponseEntity<ClientData> findByCpf(@RequestParam("cpf") String cpf);
}
