package com.sorokin.client_processing.repositories;

import com.sorokin.client_processing.models.ClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientProductRepository extends JpaRepository<ClientProduct,Long> {
    public boolean existsByProductIdAndClientId(Long productId,Long ClientId);
}
