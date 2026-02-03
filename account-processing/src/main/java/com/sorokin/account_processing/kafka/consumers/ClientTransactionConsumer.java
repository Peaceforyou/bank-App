package com.sorokin.account_processing.kafka.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ClientTransactionConsumer {

    @KafkaListener(topics = "client_transactions",
            groupId = "account-service-group")
    public void handleClientTransaction(String message) {
        // clientTransactionService.handleTransaction();
    }
}
