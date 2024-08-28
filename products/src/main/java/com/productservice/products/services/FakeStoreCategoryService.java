package com.productservice.products.services;

import com.productservice.products.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService {
    private RestTemplate restTemplate;
    private static final String FAKESTORE_CATEGORIES_URL = "https://fakestoreapi.com/products/categories";

    public FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    @Override
    public List<Category> getAllCategories() {
        String[] categories = restTemplate.getForObject(FAKESTORE_CATEGORIES_URL, String[].class);
        List<Category> categoriesList = new ArrayList<>();
        Long idCnt= 1L;
        for (String category : categories) {
            Category categoryObj = new Category();
            categoryObj.setDescription(category);
            categoryObj.setId(idCnt++);
            categoriesList.add(categoryObj);
        }
        return categoriesList;
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }
}
