package com.sorokin.client_processing.controllers;


import com.sorokin.client_processing.DTO.ClientRegistrationRequest;
import com.sorokin.client_processing.exceptions.UserAlreadyExistException;
import com.sorokin.client_processing.models.Client;
import com.sorokin.client_processing.models.User;
import com.sorokin.client_processing.repositories.ClientRepository;
import com.sorokin.client_processing.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "HELLO!";
    }


    @PostMapping("/register")
    public String registerClient(@RequestBody ClientRegistrationRequest request) {

        User user = User.builder().login(request.getLogin()).
                password(request.getPassword()). //TODO HASH
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

        try {
            clientService.registerClient(client,user);
            return "USER HAVE BEEN REGISTERED";
        }
        catch (UserAlreadyExistException e1) {
            return "USER ALREADY EXIST";
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
