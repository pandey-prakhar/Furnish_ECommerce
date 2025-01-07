package com.productservice.products.controllers;

import com.productservice.products.exeptions.ProductNotFoundExeption;
import com.productservice.products.models.Product;
import com.productservice.products.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductController productController;

    @MockBean(name = "selfProductService")
    ProductService productService;

    //Testing getAllProducts endpoint.
    @Test
    void validGetAllProducts(){
        //Arrange
        ArrayList<Product> productsFromService = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Hinge from Cryatal");
        product1.setDescription("Hinges");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Hinges from Fabista");
        product2.setDescription("Hinges");

        productsFromService.add(product1);
        productsFromService.add(product2);
        when(productService.getAllProducts()).thenReturn(productsFromService);

        //Act
        ProductController productController = new ProductController(productService);
        List<Product> products = productController.getAllProducts().getBody();

        //Assert
        assertIterableEquals(productsFromService, products);
    }

    //Testing getProductById endpoint.
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

    @Test
    void invalidGetProductByIdThrowsException() throws ProductNotFoundExeption {
        //Arrange
        Long productId = 100L;
        String message = "Product not found";
        when(productService.getProductById(productId)).thenThrow(new ProductNotFoundExeption(productId,message));
        ProductController productController = new ProductController(productService);
        //Act & Assert
        assertThrows(ProductNotFoundExeption.class, () -> productController.getProductById(productId).getBody());
    }

    //Testing replaceProducts endpoint.
    @Test
    void testValidReplaceProduct() throws ProductNotFoundExeption {
        //Arrange
        Product originalProduct = new Product();
        originalProduct.setId(1L);
        originalProduct.setTitle("original Title");
        originalProduct.setPrice(130.0);

        Product passedProduct = new Product();
        passedProduct.setTitle("passed Title");
        passedProduct.setPrice(135.0);

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setTitle(passedProduct.getTitle());
        updatedProduct.setPrice(passedProduct.getPrice());

        when(productService.replaceProduct(1L,passedProduct)).thenReturn(updatedProduct);
        ProductController productController = new ProductController(productService);

        //Act
        ResponseEntity<Product> returnedProduct = productController.replaceProduct(1L,passedProduct);

        //Assert
        assertEquals(200, returnedProduct.getStatusCodeValue());
        assertEquals(updatedProduct, returnedProduct.getBody());
    }







}