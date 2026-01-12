package com.sorokin.client_processing.models;


import com.sorokin.client_processing.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "client_product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClientProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id",nullable = false,length = 12)
    private String clientId;

    @Column(name = "product_id",nullable = false)
    private Long productId;

    @Column(name = "open_date",nullable = false)
    private LocalDate openDate;


    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private ProductStatus status;

}
