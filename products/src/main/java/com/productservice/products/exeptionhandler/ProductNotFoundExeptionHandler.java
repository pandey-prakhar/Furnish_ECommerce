package com.productservice.products.exeptionhandler;

import com.productservice.products.dtos.ProductNotFoundExeptionDto;
import com.productservice.products.exeptions.ProductNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductNotFoundExeptionHandler {
    @ExceptionHandler(ProductNotFoundExeption.class)
    public ResponseEntity<ProductNotFoundExeptionDto> handleProductNotFound(ProductNotFoundExeption productNotFoundExeption) {
        ProductNotFoundExeptionDto productNotFoundExeptionDto = new ProductNotFoundExeptionDto();
        productNotFoundExeptionDto.setMessage(productNotFoundExeption.getMessage());
        productNotFoundExeptionDto.setId(productNotFoundExeption.getId());
        return new ResponseEntity<>(productNotFoundExeptionDto,HttpStatus.NOT_FOUND);
    }
}
