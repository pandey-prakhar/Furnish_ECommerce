package com.productservice.products.services;

import com.productservice.products.models.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(Long id);
}
