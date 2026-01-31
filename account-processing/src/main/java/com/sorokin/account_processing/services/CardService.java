package com.sorokin.account_processing.services;

import com.sorokin.account_processing.DTO.CardCreationEvent;
import com.sorokin.account_processing.models.Account;
import com.sorokin.account_processing.enums.Status;
import com.sorokin.account_processing.exceptions.AccountBlockedException;
import com.sorokin.account_processing.models.Card;
import com.sorokin.account_processing.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.SecureRandom;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountService accountService;

    @Autowired
    public CardService(CardRepository cardRepository, AccountService accountService) {
        this.cardRepository = cardRepository;
        this.accountService = accountService;
    }

    @Transactional
    public void addCard(CardCreationEvent event) {
        if (accountService.checkIfInBlackList(event.getClientId())) {
            throw new AccountBlockedException("Account is in black list");
        }

        Account account = accountService.findByClientId(event.getClientId());

        Card card = Card.builder()
                .cardId(generateCardNumber())
                .status(Status.ACTIVE)
                .paymentSystem(event.getPaymentSystem())
                .accountId(account.getId())
                .build();
        cardRepository.save(card);
        account.setCardExist(true);

    }


    //simple realistion card number generating
    public String generateCardNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }

        while (cardRepository.existsByCardId(sb.toString())) {
            sb.setLength(0);
            for (int i = 0; i < 16; i++) {
                sb.append(random.nextInt(10));
            }
        }

        return sb.toString();
    }

}
