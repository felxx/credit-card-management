package com.felxx.client_microservice.application.representation;

import com.felxx.client_microservice.domain.Client;

import lombok.Data;

@Data
public class ClientSaveRequest {

    private String cpf;
    private String name;
    private Integer age;

    public Client toClient() {
        return new Client(cpf, name, age);
    }
}
