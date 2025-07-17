package com.felxx.credit_evaluator_microservice.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnClientEvaluation {

    private List<ApprovedCard> approvedCards;
}
