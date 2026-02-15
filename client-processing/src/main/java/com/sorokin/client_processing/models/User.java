package com.sorokin.client_processing.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login",
            nullable = false,
            unique = true)
    private String login;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",
            nullable = false,
            unique = true)
    private String email;


    @OneToOne(mappedBy = "user")
    private Client client;
}
