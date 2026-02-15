package com.sorokin.client_processing.services;

import com.sorokin.client_processing.exceptions.DocumentAlreadyExistException;
import com.sorokin.client_processing.models.User;
import com.sorokin.client_processing.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService service;

    private String login;
    private String password;
    private String encodedPassword;
    private String email;

    @BeforeEach
    void init() {
        login = "loginlogin";
        password = "SecretPassword";
        encodedPassword = "SCRTPSSWRD1";
        email = "emailemail@gmail.com";
    }

    @DisplayName("Should register user")
    @Test
    void shouldRegisterUser() {
        when(userRepository.existsByLogin(login)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        User user = service.registerUser(login,password,email);

        assertAll(
                () -> assertEquals(login,user.getLogin()),
                () -> assertEquals(encodedPassword,user.getPassword()),
                () -> assertEquals(email,user.getEmail()),
                () -> verify(userRepository).existsByLogin(login),
                () -> verify(userRepository).save(any(User.class))

        );
    }

    @DisplayName("Should throw exception")
    @Test
    void shouldThrowExceptionWithUserExist() {
    when(userRepository.existsByLogin(login)).thenReturn(true);

    assertThrows(DocumentAlreadyExistException.class,
            () -> service.registerUser(login,password,email));

    verify(userRepository,never()).save(any(User.class));
    }



}