package com.tts.ecommerce.model;

import lombok.*;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private String id;

    private String name;
    private String brand;
    private String description;
    private String category;
    private String image;

    private int quantity;
    private float price;

    public void setId(String id) {
        this.id = id;
    }
}