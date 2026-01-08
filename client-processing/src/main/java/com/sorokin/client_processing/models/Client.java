package com.sorokin.client_processing.models;

import com.sorokin.client_processing.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "client")
@Getter
@Setter
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "client_id",nullable = false,unique = true,length = 12)
    private String clientId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name",nullable = false)
    private String firstName;


    @Column(name = "middle_name",nullable = false)
    private String middleName;


    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "date_of_birth",nullable = false)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type",nullable = false)
    private DocumentType documentType;

    @Column(name = "documentId",nullable = false)
    private String documentId;

    @Column(name = "document_prefix")
    private String documentPrefix;

    @Column(name = "document_suffix")
    private String documentSuffix;







}
