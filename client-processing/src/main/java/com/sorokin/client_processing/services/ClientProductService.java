package com.sorokin.client_processing.services;


import com.sorokin.client_processing.DTO.ClientProductEvent;
import com.sorokin.client_processing.enums.ProductStatus;
import com.sorokin.client_processing.exceptions.ClientProductException;
import com.sorokin.client_processing.exceptions.ProductNotFoundException;
import com.sorokin.client_processing.kafka.producers.ClientProductProducer;
import com.sorokin.client_processing.models.ClientProduct;
import com.sorokin.client_processing.models.Product;
import com.sorokin.client_processing.repositories.ClientProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
public class ClientProductService {

    private ClientProductProducer clientProductProducer;
    private ClientProductRepository clientProductRepository;
    private ProductService productService;

    @Autowired
    public ClientProductService(ClientProductProducer clientProductProducer, ClientProductRepository clientProductRepository, ProductService productService) {
        this.clientProductProducer = clientProductProducer;
        this.clientProductRepository = clientProductRepository;
        this.productService = productService;
    }

    @Transactional
    public  void openProduct(Long productId,Long clientId) {
        Product product;
        try {
            product  = productService.find(productId);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException(e.getMessage());
        }

        if (clientProductRepository.existsByProductIdAndClientId(productId,clientId)){
            throw new ClientProductException("Client already own this product");
        }


        ClientProduct clientProduct = ClientProduct.builder().openDate(LocalDate.now())
                .productId(productId)
                .clientId(clientId.toString())
                .status(ProductStatus.ACTIVE)
                .openDate(LocalDate.now())
                .build();
        clientProductRepository.save(clientProduct);


        ClientProductEvent clientProductEvent = ClientProductEvent.builder().clientId(clientId.toString())
                .productId(productId).
                productKey(product.getKey())
                .openDate(LocalDate.now()).
                build();
        clientProductProducer.send(product.getKey().getTopic(),clientProductEvent);








    }




}
