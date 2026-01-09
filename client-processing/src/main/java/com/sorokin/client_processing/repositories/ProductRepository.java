package com.sorokin.client_processing.repositories;

import com.sorokin.client_processing.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public boolean existsByName(String name);
    public Optional<Product> findById(Long id);
    public void deleteById(Long id);
    public boolean existsById(Long id);
}
