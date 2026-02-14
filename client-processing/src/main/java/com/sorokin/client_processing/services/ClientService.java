package com.sorokin.client_processing.services;


import com.sorokin.client_processing.DTO.ClientRegistrationRequest;
import com.sorokin.client_processing.exceptions.RegisterClientException;
import com.sorokin.client_processing.exceptions.UserAlreadyExistException;
import com.sorokin.client_processing.models.Client;
import com.sorokin.client_processing.models.User;
import com.sorokin.client_processing.repositories.ClientRepository;
import com.sorokin.client_processing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientRepository clientRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = false)
    public void registerClient(ClientRegistrationRequest request) throws RegisterClientException {

        User user = User.builder().login(request.getLogin()).
                password(passwordEncoder.encode(request.getPassword())).
                email(request.getEmail())
                .build();


        Client client = Client.builder().firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .documentType(request.getDocumentType())
                .documentId(request.getDocumentId())
                .documentPrefix(request.getDocumentPrefix())
                .documentSuffix(request.getDocumentSuffix())
                .build();

        if (clientRepository.existsByDocumentId(client.getDocumentId())) {
            throw new UserAlreadyExistException("User already exist!");};



        //Add new user
        userRepository.save(user);

        //make link between user and client
        client.setUser(user);

        String clientId = generateClientId();
        client.setClientId(clientId);
        clientRepository.save(client);



    }

    private String generateClientId() {
        //simple realisation
        long count = clientRepository.count();
        return String.format("77010%07d", count + 1);
    }
}
