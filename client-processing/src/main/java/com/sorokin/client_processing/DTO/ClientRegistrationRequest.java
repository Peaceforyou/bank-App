package com.sorokin.client_processing.DTO;

import com.sorokin.client_processing.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRegistrationRequest {
    private String login;
    private String password;
    private String email;

    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;

    private DocumentType documentType;
    private String documentId;
    private String documentPrefix;
    private String documentSuffix;
}