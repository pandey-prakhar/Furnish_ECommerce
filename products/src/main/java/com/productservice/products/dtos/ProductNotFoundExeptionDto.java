package com.productservice.products.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExeptionDto {
    private Long id;
    private String Message;
}
