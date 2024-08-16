package com.productservice.products.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private  String description;
    private String category;
    private String image;
}
