package com.REST.blogapi.payloads.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryDto {

    private int categoryId;
    
    @NotEmpty
    @Size(min=4, message="Category title must be min of 4 characters")
    private String categoryTitle;

        
    @NotEmpty
    private String categoryDescription;

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryTitle() {
        return categoryTitle;
    }
    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
    public String getCategoryDescription() {
        return categoryDescription;
    }
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    
}
