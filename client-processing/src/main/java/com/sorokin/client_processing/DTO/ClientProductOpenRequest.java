package com.sorokin.client_processing.DTO;

import lombok.Data;

@Data
public class ClientProductOpenRequest {

    private String clientId;


    private Long productId;
}
