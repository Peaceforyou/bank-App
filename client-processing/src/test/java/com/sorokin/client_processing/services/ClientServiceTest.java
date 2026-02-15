package com.sorokin.client_processing.services;

import com.sorokin.client_processing.DTO.ClientRegistrationRequest;
import com.sorokin.client_processing.enums.DocumentType;
import com.sorokin.client_processing.exceptions.DocumentAlreadyExistException;
import com.sorokin.client_processing.models.Client;
import com.sorokin.client_processing.models.User;
import com.sorokin.client_processing.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService service;

    private ClientRegistrationRequest request;

    private User user;


    @BeforeEach
    void init() {
         request = ClientRegistrationRequest.builder()
                .firstName("Max")
                .middleName("Alexandrovich")
                .lastName("Kon")
                 .dateOfBirth(Date.valueOf("1990-05-15"))
                .documentType(DocumentType.INT_PASSPORT)
                .documentId("72 5678901")
                .documentPrefix("72")
                .documentSuffix("17")
                .build();

         user = User.builder()
                .login("Max123")
                .password("hashed_password")
                .email("max@mail.ru")
                .build();
    }

    @DisplayName("Should throw exception")
    @Test
    void shouldThrowExceptionWhenDocumentAlreadyExists() {

        when(clientRepository.existsByDocumentId("72 5678901")).thenReturn(true);

        //act assert
        assertThrows(DocumentAlreadyExistException.class,
                () -> service.registerClient(request, user));

        verify(clientRepository, never()).save(any(Client.class));
    }

    @DisplayName("Should register client")
    @Test
    void shouldGenerateClientIdAndSave(){

        when(clientRepository.existsByDocumentId(any())).thenReturn(false);
        when(clientRepository.count()).thenReturn(0L);
        when(clientRepository.save(any(Client.class))).thenAnswer(ans -> ans.getArgument(0));

        Client result = service.registerClient(request,user);


        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("770100000001", result.getClientId()),
                () -> assertEquals(request.getFirstName(), result.getFirstName()),
                () -> assertEquals(request.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(request.getLastName(), result.getLastName()),
                () -> assertEquals(request.getDateOfBirth(), result.getDateOfBirth()),
                () -> assertEquals(request.getDocumentType(), result.getDocumentType()),
                () -> assertEquals(request.getDocumentId(), result.getDocumentId()),
                () -> assertEquals(request.getDocumentPrefix(), result.getDocumentPrefix()),
                () -> assertEquals(request.getDocumentSuffix(), result.getDocumentSuffix()),
                () -> assertEquals(user, result.getUser())
        );
}}