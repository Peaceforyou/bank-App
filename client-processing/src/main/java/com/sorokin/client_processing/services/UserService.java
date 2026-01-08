package com.sorokin.client_processing.services;


import com.sorokin.client_processing.exceptions.UserAlreadyExistException;
import com.sorokin.client_processing.models.User;
import com.sorokin.client_processing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = false)
    public void registerUser(User user) throws Exception {
        // blackListRepository.check();
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new UserAlreadyExistException("User already exist!");};
            userRepository.save(user);

}}
