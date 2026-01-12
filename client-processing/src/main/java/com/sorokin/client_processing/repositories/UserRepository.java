package com.sorokin.client_processing.repositories;

import com.sorokin.client_processing.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public boolean existsByLogin(String login);
}
