package com.example.springmvc.service;

import com.example.springmvc.dto.CategoryRequest;
import com.example.springmvc.dto.CategoryResponse;
import com.example.springmvc.model.Category;
import com.example.springmvc.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService
{
    private final CategoryRepository categoryProductRepository;
    private CategoryResponse MapCategoryResopne(Category category)
    {
        return CategoryResponse.builder()
                .id(category.getId())
                .description(category.getDescription())
                .title(category.getTitle())
                .build();
    }
    private Category MapCategoryRequest(CategoryRequest categoryRequest)
    {
        return Category.builder()
                .title(categoryRequest.title())
                .description(categoryRequest.description())
                .build();
    }
    private Category getCategoryById(int id)
    {
        return categoryProductRepository.getAllCategory().stream()
                .filter(pro->pro.getId()==id).findFirst()
                .orElseThrow(()->new HttpMessageConversionException("Category id="+id+"not found"));
    }
    @Override
    public List<CategoryResponse> getAllCategory(String name) {
        var getCategory=categoryProductRepository.getAllCategory();
        if (!name.isEmpty())
        {
            getCategory= getCategory.stream().filter(
                    category->category.getTitle().toLowerCase().contains(name.toLowerCase())
            ).toList();
        }
        return categoryProductRepository.getAllCategory()
                .stream().map(this::MapCategoryResopne).toList();
    }
    @Override
    public CategoryResponse deleteCategory(int id) {
        var categoryFind=getCategoryById(id);
        categoryProductRepository.getAllCategory().remove(categoryFind);
        return MapCategoryResopne(categoryFind);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        var category=MapCategoryRequest(categoryRequest);
        categoryProductRepository.getAllCategory().stream().max(Comparator.comparing(Category::getId))
                .map(Category::getId).ifPresentOrElse(
                        id->category.setId(id+1),
                        ()->category.setId(1));
        categoryProductRepository.addCategory(category);
        return MapCategoryResopne(category);
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryRequest categoryRequest) {
        var getCategory=getCategoryById(id);
        getCategory.setTitle(categoryRequest.title());
        getCategory.setDescription(categoryRequest.description());
        return MapCategoryResopne(getCategory);
    }

    @Override
    public CategoryResponse getCategory(int id) {
        var getGetegory=getCategoryById(id);
        return MapCategoryResopne(getGetegory);
    }
}