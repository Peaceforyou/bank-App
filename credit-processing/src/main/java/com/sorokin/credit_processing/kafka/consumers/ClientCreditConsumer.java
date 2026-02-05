package com.sorokin.credit_processing.kafka.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ClientCreditProductConsumer {

    @KafkaListener(topics = "client_credit_products",
            groupId = "credit-service-group")
    public void handleClientCreditProduct(String message) {
        // clientCreditProductService.handleCreditProduct();
    }
}