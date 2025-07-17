package com.felxx.credit_evaluator_microservice.application.exception;

public class MicroserviceCommunicationErrorException extends RuntimeException {
    public MicroserviceCommunicationErrorException(String message) {
        super(message);
    }
}
