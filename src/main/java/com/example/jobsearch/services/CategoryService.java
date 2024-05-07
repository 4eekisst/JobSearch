package com.example.jobsearch.services;

import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.models.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryByName(String category);
    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getCategories();
}