package com.productservice.products.services;

import com.productservice.products.exeptions.ProductNotFoundExeption;
import com.productservice.products.models.Product;
import com.productservice.products.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("selfProductService")
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product getProductById(Long Id) throws ProductNotFoundExeption {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProduct(Long Id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long Id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long Id) {

    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
