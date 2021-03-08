package com.tts.ecommerce.model;

import lombok.*;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;
    private float price;

    private String name;
    private String brand;
    private String description;
    private String category;
    private String image;

    public Product(int quantity, float price, String name, String brand, String description, String category, String image) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.category = category;
        this.image = image;
    }
}