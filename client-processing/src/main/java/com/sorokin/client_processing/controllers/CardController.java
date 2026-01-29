package com.sorokin.client_processing.controllers;


import com.sorokin.client_processing.DTO.CardOpenRequest;
import com.sorokin.client_processing.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/api/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping()
     public ResponseEntity<String> createCard(@RequestBody CardOpenRequest request) {

        cardService.createCard(request.getAccountId(),request.getPaymentSystem());


        return ResponseEntity.ok("Card creation is processing");

     }

}
