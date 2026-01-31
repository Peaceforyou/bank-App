package com.sorokin.account_processing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCreationEvent {
    private String clientId;
    private String paymentSystem;
    private String requestDate;
}