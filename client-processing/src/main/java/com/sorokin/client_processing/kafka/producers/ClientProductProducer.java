package com.sorokin.client_processing.kafka.producers;


import com.sorokin.client_processing.DTO.ClientProductEvent;
import com.sorokin.client_processing.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClientProductProducer {

    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public ClientProductProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, ClientProductEvent clientProductEvent) {
        kafkaTemplate.send(topic,clientProductEvent);
        System.out.println("KAFKA sent to topic '" + topic + "': " + clientProductEvent);
    }


}
