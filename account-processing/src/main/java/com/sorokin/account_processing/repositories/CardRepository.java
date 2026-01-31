package com.sorokin.account_processing.repositories;

import com.sorokin.account_processing.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    public boolean existsByCardId(String cardId);
}
