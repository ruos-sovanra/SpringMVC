package com.example.springmvc.service;

import com.example.springmvc.dto.CategoryRequest;
import com.example.springmvc.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategory(String name);
    CategoryResponse deleteCategory(int id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(int id, CategoryRequest categoryRequest);
    CategoryResponse getCategory(int id);
}
