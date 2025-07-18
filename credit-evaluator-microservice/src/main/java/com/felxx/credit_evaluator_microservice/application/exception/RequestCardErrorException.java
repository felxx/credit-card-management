package com.felxx.credit_evaluator_microservice.application.exception;

public class RequestCardErrorException extends RuntimeException {

    public RequestCardErrorException(String message) {
        super(message);
    }
}
