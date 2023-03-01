package com.REST.blogapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.REST.blogapi.entities.Category;
import com.REST.blogapi.exceptions.ResourceNotFoundException;
import com.REST.blogapi.payloads.CategoryDto;
import com.REST.blogapi.repositories.CategoryRepo;
import com.REST.blogapi.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto createCategory(CategoryDto categoryDto){

        Category category = this.modelMapper.map(categoryDto, Category.class);

        Category newCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(newCategory,CategoryDto.class);
    }


    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId){
        
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updaCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(updaCategory, CategoryDto.class);
    }

    
    public CategoryDto getCategoryById(Integer categoryId){

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
        
        return this.modelMapper.map(category, CategoryDto.class);
    }

    
    public List<CategoryDto> getAllCategories(){

        List<Category> categories = this.categoryRepo.findAll();

        return categories.stream().map(category->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    
    }

    public void deleteCategory(Integer categoryId){
        
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
        
        this.categoryRepo.delete(category);
    }

}
