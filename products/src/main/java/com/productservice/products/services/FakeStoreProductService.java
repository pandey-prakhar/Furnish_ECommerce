package com.productservice.products.services;

import com.productservice.products.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    @Override
    public Product getProductById(Long Id) {
        return new Product();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
