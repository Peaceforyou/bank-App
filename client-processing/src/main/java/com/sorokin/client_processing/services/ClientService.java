package com.sorokin.client_processing.services;


import com.sorokin.client_processing.DTO.ClientRegistrationRequest;
import com.sorokin.client_processing.exceptions.DocumentAlreadyExistException;
import com.sorokin.client_processing.models.Client;
import com.sorokin.client_processing.models.User;
import com.sorokin.client_processing.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = false)
    public Client registerClient(ClientRegistrationRequest request, User user) throws DocumentAlreadyExistException {

        Client client = Client.builder().firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .documentType(request.getDocumentType())
                .documentId(request.getDocumentId())
                .documentPrefix(request.getDocumentPrefix())
                .documentSuffix(request.getDocumentSuffix())
                .user(user)
                .build();

        if (clientRepository.existsByDocumentId(client.getDocumentId())) {
            throw new DocumentAlreadyExistException("Document already exist!");}

        client.setClientId(generateClientId());
        clientRepository.save(client);

        return client;
    }

    private String generateClientId() {
        //simple realisation
        long count = clientRepository.count();
        return String.format("77010%07d", count + 1);
    }
}
