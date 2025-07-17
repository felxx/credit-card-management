package com.felxx.card_microservice.infra.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felxx.card_microservice.application.domain.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<List<Card>> findByIncomeLessThanEqual(BigDecimal incomeBigDecimal);
}
