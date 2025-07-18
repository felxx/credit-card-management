package com.felxx.credit_evaluator_microservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.card-emission}")
    private String cardEmissionQueueName;
    
    public Queue cardEmissionQueue() {
        return new Queue(cardEmissionQueueName, true);
    }
}
