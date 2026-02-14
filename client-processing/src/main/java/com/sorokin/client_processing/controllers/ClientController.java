package com.sorokin.client_processing.controllers;


import com.sorokin.client_processing.DTO.ClientRegistrationRequest;
import com.sorokin.client_processing.exceptions.DocumentAlreadyExistException;
import com.sorokin.client_processing.services.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientController {

    private final RegistrationService registrationService;

    public ClientController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }



    @PostMapping("/register")
    public String createClientForUser(@RequestBody ClientRegistrationRequest request) {
        try {
            registrationService.registerClient(request);
            return "USER HAVE BEEN REGISTERED";
        }
        catch (DocumentAlreadyExistException e1) {
            return "USER ALREADY EXIST";
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
