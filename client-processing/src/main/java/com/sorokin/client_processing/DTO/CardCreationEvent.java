package com.sorokin.client_processing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCreationEvent {
    private Long accountId;
    private String paymentSystem;
    private String requestDate;
}