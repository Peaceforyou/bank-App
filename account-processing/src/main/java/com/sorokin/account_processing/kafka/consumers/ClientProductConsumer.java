package com.sorokin.account_processing.kafka.consumers;

import com.sorokin.account_processing.DTO.ClientProductEvent;
import com.sorokin.account_processing.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
public class ClientProductConsumer {

    private final AccountService accountService;

    @Autowired
    public ClientProductConsumer(AccountService accountService) {
        this.accountService = accountService;
    }

    @KafkaListener(topics = "client_products",
            groupId = "account-service-group",
            properties = {"spring.json.value.default.type=com.sorokin.account_processing.DTO.ClientProductEvent"})
    public void handleClientProduct(ClientProductEvent event) {
        accountService.openAccount(event);
    }
}