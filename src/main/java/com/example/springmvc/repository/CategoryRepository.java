package com.example.springmvc.repository;

import com.example.springmvc.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryRepository {
    private final List<Category> categories=new ArrayList<>(){{
        add(Category.builder()
                .id(1)
                .title("Category1")
                .description("description").
                build());
        add(Category.builder()
                .id(2)
                .description("description2")
                .title("Category2")
                .build());
    }};
    public List<Category> getAllCategory()
    {
        return categories;
    }
    public void addCategory(Category category)
    {
        categories.add(category);
    }

}
