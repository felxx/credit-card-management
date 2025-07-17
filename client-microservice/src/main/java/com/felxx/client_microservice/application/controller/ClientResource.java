package com.felxx.client_microservice.application.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felxx.client_microservice.application.representation.ClientSaveRequest;
import com.felxx.client_microservice.application.service.ClientService;
import com.felxx.client_microservice.domain.Client;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientResource {
    
    private final ClientService clientService;
    
    @GetMapping
    public String status(){
        return "Client microservice is running!";
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClientSaveRequest request) {
        Client client = request.toClient();
        clientService.save(client);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={}").buildAndExpand(client.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();
    } 

    @GetMapping(params = "cpf")
    public ResponseEntity findByCpf(@RequestParam("cpf") String cpf) {
        return clientService.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
}
