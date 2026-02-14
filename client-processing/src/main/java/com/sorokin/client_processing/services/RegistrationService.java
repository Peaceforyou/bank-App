package com.sorokin.client_processing.services;


import com.sorokin.client_processing.DTO.ClientRegistrationRequest;
import com.sorokin.client_processing.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {


    private final ClientService clientService;
    private final UserService userService;

    @Autowired
    public RegistrationService(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }




    @Transactional
    public void registerClient(ClientRegistrationRequest request) {

        User user = userService.registerUser(request.getLogin(), request.getPassword(), request.getEmail());
        clientService.registerClient(request,user);
    }
}
