package com.example.jobsearch.services;

import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.models.Category;

public interface CategoryService {
    Category getCategoryIdByName(String category);
    CategoryDto getCategoryById(Long id);
}