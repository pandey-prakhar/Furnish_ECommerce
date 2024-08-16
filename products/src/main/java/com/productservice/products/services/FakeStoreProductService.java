package com.productservice.products.services;

import com.productservice.products.dtos.ProductResponseDto;
import com.productservice.products.models.Category;
import com.productservice.products.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long Id) {
        ProductResponseDto dto = restTemplate.getForObject("https://fakestoreapi.com/products/" + Id, ProductResponseDto.class);
        //Convert DTO object to Product object to return this is done by a method we are creating
        assert dto != null;
        return convertDtoToProduct(dto);
    }

    private Product convertDtoToProduct(ProductResponseDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setDescription(dto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        ProductResponseDto[] products = restTemplate.getForObject("https://fakestoreapi.com/products", ProductResponseDto[].class);
        List<Product> productList = new ArrayList<>();
        for (ProductResponseDto dto : products) {
            productList.add(convertDtoToProduct(dto));
        }
        return productList;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
//        restTemplate.put("https://fakestoreapi.com/products/" + Id, product);// This would have worked if we were not looking for return of updated object after replace.
        ProductResponseDto dto = convertProductToDto(product);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(dto, ProductResponseDto.class);
        HttpMessageConverterExtractor<ProductResponseDto> responseExtractor = new HttpMessageConverterExtractor(ProductResponseDto.class, restTemplate.getMessageConverters());
        ProductResponseDto response=restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertDtoToProduct(response);
    }

    private ProductResponseDto convertProductToDto(Product product) {
        ProductResponseDto dto= new ProductResponseDto();
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        return dto;
    }


}
