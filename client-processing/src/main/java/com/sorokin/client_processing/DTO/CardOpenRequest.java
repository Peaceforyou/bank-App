package com.sorokin.client_processing.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardOpenRequest {

    private Long accountId;

    private String paymentSystem;

}
