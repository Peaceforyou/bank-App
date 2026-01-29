package com.sorokin.client_processing.services;

import com.sorokin.client_processing.DTO.CardCreationEvent;
import com.sorokin.client_processing.kafka.producers.CardCreationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardService {

    private final CardCreationProducer cardCreationProducer;

    @Autowired
    public CardService(CardCreationProducer cardCreationProducer) {
        this.cardCreationProducer = cardCreationProducer;
    }

    public void createCard(Long accountId, String paymentSystem) {

        CardCreationEvent event = new CardCreationEvent(
                accountId,
                paymentSystem,
                LocalDate.now().toString()
        );
        cardCreationProducer.send(event);
    }
}
