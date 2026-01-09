package com.sorokin.client_processing.controllers;

import com.sorokin.client_processing.DTO.ProductAddRequest;
import com.sorokin.client_processing.exceptions.ProductAlreadyExistException;
import com.sorokin.client_processing.exceptions.ProductNotFoundException;
import com.sorokin.client_processing.models.Product;
import com.sorokin.client_processing.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping()
    public ResponseEntity<List<Product>> products() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findOneProduct(@PathVariable Long id){
        try {
            Product product = productService.find(id);
            return ResponseEntity.ok(product);
        }
        catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public ResponseEntity<String> addProduct(@RequestBody ProductAddRequest request) {

        Product product = Product.builder()
                .key(request.getKey())
                .name(request.getName())
                .build();
        try {
            productService.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("PRODUCT WAS CREATED!");
        }
        catch (ProductAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.remove(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("PRODUCT REMOVED");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductAddRequest request) {
        try {
            productService.update(id,request.getName());
            return ResponseEntity.ok().build();
        }
        catch (ProductNotFoundException e) {
           return  ResponseEntity.notFound().build();
        }
    }

}
