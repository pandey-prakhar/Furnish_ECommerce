package com.productservice.products.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product{
    private Long id;
    private String title;
    private String description;
    private String image;
    private double price;
    private Category category;
}
