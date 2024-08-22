package com.productservice.products.exeptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExeption extends Exception {
    private Long Id;
    public ProductNotFoundExeption(Long id, String message) {
        super(message);
        this.Id = id;
    }
}
