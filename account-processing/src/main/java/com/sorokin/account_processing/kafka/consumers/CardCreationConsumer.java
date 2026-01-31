package com.sorokin.account_processing.kafka.consumers;

import com.sorokin.account_processing.DTO.CardCreationEvent;
import com.sorokin.account_processing.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CardCreationConsumer {

    private final CardService cardService;

    @Autowired
    public CardCreationConsumer(CardService cardService) {
        this.cardService = cardService;
    }


    @KafkaListener(topics = "clients_card",
            groupId = "account-service-group",
    properties = {"spring.json.value.default.type=com.sorokin.account_processing.DTO.CardCreationEvent"})
    public void handle(CardCreationEvent event) {
        System.out.println("Test got message " + event);
        try {
            cardService.addCard(event);
        }
        catch (Exception e ) {
            System.out.println(e.getMessage());
        }

    }
}
