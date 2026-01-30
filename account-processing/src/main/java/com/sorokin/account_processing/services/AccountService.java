package com.sorokin.account_processing.services;


import com.sorokin.account_processing.DTO.ClientProductEvent;
import com.sorokin.account_processing.enums.Status;
import com.sorokin.account_processing.exceptions.AccountAlreadyExistException;
import com.sorokin.account_processing.models.Account;
import com.sorokin.account_processing.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void openAccount(ClientProductEvent data) {
        Account account = Account.builder()
                .clientId(data.getClientId())
                .productId(data.getProductId())
                .balance(BigDecimal.ZERO)
                .status(Status.ACTIVE).isRecalc(false)
                .cardExist(false)
                .build();


            if (accountRepository.existsByProductIdAndClientId(Long.valueOf(account.getProductId()),
                    account.getClientId())) throw  new AccountAlreadyExistException("Acc already exist");

            accountRepository.save(account);



    }
}
