package com.REST.blogapi.services;

import java.util.List;

import com.REST.blogapi.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto category);

    CategoryDto updateCategory(CategoryDto category, Integer id);

    CategoryDto getCategoryById(Integer id);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Integer id);
}
