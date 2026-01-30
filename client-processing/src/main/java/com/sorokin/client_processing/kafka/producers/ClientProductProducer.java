package com.sorokin.client_processing.kafka.producers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sorokin.client_processing.DTO.ClientProductEvent;
import com.sorokin.client_processing.enums.ProductKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClientProductProducer {

    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public ClientProductProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ClientProductEvent clientProductEvent) {
        kafkaTemplate.send(clientProductEvent.getProductKey().getTopic(),clientProductEvent);
        System.out.println("KAFKA sent to topic '" + clientProductEvent.getProductKey().getTopic() + "': " + clientProductEvent);
    }


}