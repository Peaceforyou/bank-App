package com.sorokin.client_processing.repositories;

import com.sorokin.client_processing.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    public boolean existsByDocumentId(String documentId);
}
