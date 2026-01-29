package com.sorokin.client_processing.controllers;


import com.sorokin.client_processing.DTO.ClientRegistrationRequest;
import com.sorokin.client_processing.exceptions.UserAlreadyExistException;
import com.sorokin.client_processing.services.ClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }



    @PostMapping("/register")
    public String registerClient(@RequestBody ClientRegistrationRequest request) {
        try {
            clientService.registerClient(request);
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
