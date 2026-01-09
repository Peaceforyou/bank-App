package com.sorokin.client_processing.services;


import com.sorokin.client_processing.exceptions.ProductAlreadyExistException;
import com.sorokin.client_processing.exceptions.ProductNotFoundException;
import com.sorokin.client_processing.models.Product;
import com.sorokin.client_processing.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional(readOnly = false)
    public void save(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new ProductAlreadyExistException("Product already exists");
        }
        productRepository.save(product);
    }

    @Transactional(readOnly = false)
    public void remove(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }


    public Product find(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found with id: " + id));
        return product;
    }

    @Transactional
    public Product update(Long id, String newName) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        product.setName(newName);
        return productRepository.save(product);
    }





}
