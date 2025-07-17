package com.felxx.card_microservice.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.felxx.card_microservice.application.domain.ClientCard;
import com.felxx.card_microservice.infra.repository.ClientCardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientCardService {
    
    private final ClientCardRepository clientCardRepository;

    public List<ClientCard> findByCpf(String cpf) {
        return clientCardRepository.findByCpf(cpf);
    }
}
