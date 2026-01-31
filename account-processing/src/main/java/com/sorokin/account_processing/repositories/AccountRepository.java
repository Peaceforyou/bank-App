package com.sorokin.account_processing.repositories;

import com.sorokin.account_processing.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByProductIdAndClientId(Long productId, String clientId);
    Optional<Account> findByClientId(String clientId);
}
