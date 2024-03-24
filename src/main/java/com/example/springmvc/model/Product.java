package com.example.springmvc.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private int id;
    private String title;
    private String description;
    private float price;
    private String imageUrl;

}
