package com.REST.blogapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.REST.blogapi.constants.MessageConstants;
import com.REST.blogapi.payloads.ApiResponse;
import com.REST.blogapi.payloads.CategoryDto;
import com.REST.blogapi.services.CategoryService;
import com.REST.blogapi.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/category")
@ComponentScan(basePackageClasses = CategoryServiceImpl.class)
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto category = this.categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int categoryId) {

        CategoryDto category = this.categoryService.getCategoryById(categoryId);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        List<CategoryDto> categories = this.categoryService.getAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto CategoryDto, @PathVariable int categoryId) {

        CategoryDto category = this.categoryService.updateCategory(CategoryDto, categoryId);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ApiResponse deleteuser(@PathVariable int categoryId) {

        this.categoryService.deleteCategory(categoryId);

        return new ApiResponse(MessageConstants.CATEGORY_DELETE_SUCCESS, true);

    }
}
