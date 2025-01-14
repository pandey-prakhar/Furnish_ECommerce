package com.productservice.products.controllers;

import com.productservice.products.commons.AuthCommans;
import com.productservice.products.dtos.UserDto;
import com.productservice.products.exeptions.ProductNotFoundExeption;
import com.productservice.products.models.Product;
import com.productservice.products.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private AuthCommans authCommans;

    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthCommans authCommans) {
        this.productService = productService;
        this.authCommans = authCommans;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products); // Returns 200 OK with the list of products
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id, @RequestHeader("authToken") String token ) throws ProductNotFoundExeption {

        UserDto userDto= authCommans.validateToken(token);

        if (userDto == null) {
            ResponseEntity<Product> responseEntity= new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }

        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product); // Returns 200 OK with the product
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundExeption {
        Product replacedProduct = productService.replaceProduct(id, product);
        return ResponseEntity.ok(replacedProduct); // Returns 200 OK with the replaced product
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundExeption {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct); // Returns 200 OK with the updated product
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct); // Returns 201 Created with the new product
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundExeption {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }
}
