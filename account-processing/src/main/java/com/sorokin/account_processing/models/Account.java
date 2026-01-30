package com.sorokin.account_processing.models;

import com.sorokin.account_processing.enums.Status;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id",nullable = false,unique = true,length = 12)
    private String clientId;

    @Column(name = "product_id", updatable = false)
    private Long productId;


    @Column(name = "balance",nullable = false)
    private BigDecimal balance;


    @Column(name = "interest_rate",nullable = true)
    private BigDecimal interestRate;

    @Column(name = "is_recalc")
    private Boolean isRecalc = false;

    @Column(name="card_exist",nullable = false)
    private Boolean cardExist = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

}
