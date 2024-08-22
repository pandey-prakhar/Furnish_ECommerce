package com.productservice.products.services;

import com.productservice.products.exeptions.ProductNotFoundExeption;
import com.productservice.products.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long Id) throws ProductNotFoundExeption;
    List<Product> getAllProducts();
    Product replaceProduct(Long Id, Product product);
}
