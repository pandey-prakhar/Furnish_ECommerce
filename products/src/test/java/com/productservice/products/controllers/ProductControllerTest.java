package com.productservice.products.controllers;

import com.productservice.products.exeptions.ProductNotFoundExeption;
import com.productservice.products.models.Product;
import com.productservice.products.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductController productController;

    @MockBean(name = "selfProductService")
    ProductService productService;

    @Test
    void validGetProductById() throws ProductNotFoundExeption {
        //Arrange
        Product productFromService = new Product();
        productFromService.setId(1L);
        productFromService.setTitle("Hinge from fab");
        productFromService.setDescription("Hinges");
        when(productService.getProductById(1L)).thenReturn(productFromService);
        //Act
        Product productFromController= productController.getProductById(1L).getBody();// getBody method extracts product from response entity.
        //Assert
        assertEquals(productFromService, productFromController);
    }
}