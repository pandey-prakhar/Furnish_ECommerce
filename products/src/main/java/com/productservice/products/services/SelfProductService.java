package com.productservice.products.services;

import com.productservice.products.exeptions.ProductNotFoundExeption;
import com.productservice.products.models.Category;
import com.productservice.products.models.Product;
import com.productservice.products.repositories.CategoryRepository;
import com.productservice.products.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long Id) throws ProductNotFoundExeption {
        Optional<Product> productOptional = productRepository.findById(Id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundExeption(Id, "This is not a valid product");
        }
        return productOptional.get();
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
        //To solve the error of "references an unsaved transient instance" as category must be there before saving the project.
        Category category = product.getCategory();
        if (category.getId() == null) {
            category= categoryRepository.save(category);
            product.setCategory(category);
        }
        else {
            //Category is valid.
        }
        //As fetch type is lazy as one category can have many products so we will have to fetch all details.
        Product savedProduct = productRepository.save(product);
        Optional<Category> categoryOptional = categoryRepository.findById(savedProduct.getCategory().getId());
        Category category1 = categoryOptional.get();
        savedProduct.setCategory(category1);

        return savedProduct;
    }
}