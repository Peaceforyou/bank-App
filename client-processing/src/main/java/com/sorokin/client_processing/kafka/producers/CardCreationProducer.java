package com.sorokin.client_processing.kafka.producers;

import com.sorokin.client_processing.DTO.CardCreationEvent;
import com.sorokin.client_processing.DTO.ClientProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CardCreationProducer {

    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public CardCreationProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(CardCreationEvent cardCreationEvent) {
        kafkaTemplate.send("clients_card",cardCreationEvent);
        System.out.println("card creation request sent to topic" +  cardCreationEvent);
    }


}

