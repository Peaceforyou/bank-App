package com.sorokin.client_processing.models;

import com.sorokin.client_processing.enums.ProductKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "key")
    private ProductKey key;

    @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;

    @Column(name = "product_id", insertable = false, updatable = false)
    private String productId;

}
