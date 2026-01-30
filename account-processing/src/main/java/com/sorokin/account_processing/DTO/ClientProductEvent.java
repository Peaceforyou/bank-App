package com.sorokin.account_processing.DTO;


import com.sorokin.account_processing.enums.ProductKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientProductEvent {

    private String clientId;

    private Long productId;

    private ProductKey productKey;

    private LocalDate openDate;


}
