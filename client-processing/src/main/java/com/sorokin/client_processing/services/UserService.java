package com.sorokin.client_processing.services;


import com.sorokin.client_processing.DTO.ClientRegistrationRequest;
import com.sorokin.client_processing.exceptions.DocumentAlreadyExistException;
import com.sorokin.client_processing.models.User;
import com.sorokin.client_processing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = false)
    public User registerUser(String login, String password, String email) throws DocumentAlreadyExistException, IllegalArgumentException {
        // blackListRepository.check();

        User userToSave = User.builder().login(login).
                password(passwordEncoder.encode(password)).
                email(email)
                .build();

        if (userRepository.existsByLogin(login)) {
            throw new DocumentAlreadyExistException("User already exist!");}

        userRepository.save(userToSave);

        return userToSave;

}}
