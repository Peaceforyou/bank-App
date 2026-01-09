package com.sorokin.client_processing.DTO;


import com.sorokin.client_processing.enums.ProductKey;
import lombok.Data;



@Data
public class ProductAddRequest {

    private String name;


    private ProductKey key;


}
