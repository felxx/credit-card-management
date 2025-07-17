package com.felxx.card_microservice.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felxx.card_microservice.application.domain.ClientCard;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {
    
    List<ClientCard> findByCpf(String cpf);
}
