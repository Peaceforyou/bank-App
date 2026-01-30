package com.sorokin.account_processing.repositories;

import com.sorokin.account_processing.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    public boolean existsByProductIdAndClientId(Long productId,String ClientId);
}
