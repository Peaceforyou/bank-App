package com.sorokin.account_processing.models;

import com.sorokin.account_processing.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "account_id",nullable = false,unique = true)
    private Long accountId;

    @Column(name = "card_id",nullable = false,unique = true)
    private String cardId;

    @Column(name = "payment_system",nullable = false)
    private String paymentSystem;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;


}
