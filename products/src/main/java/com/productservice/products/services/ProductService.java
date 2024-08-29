package com.productservice.products.services;

import com.productservice.products.exeptions.ProductNotFoundExeption;
import com.productservice.products.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long Id) throws ProductNotFoundExeption;
    List<Product> getAllProducts();
    Product replaceProduct(Long Id, Product product);
    Product updateProduct(Long Id, Product product);
    void deleteProduct(Long Id);
    Product createProduct(Product product);
}
