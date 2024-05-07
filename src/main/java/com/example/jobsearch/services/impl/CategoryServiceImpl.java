package com.example.jobsearch.services.impl;

import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.exceptions.CategoryNotFoundException;
import com.example.jobsearch.models.Category;
import com.example.jobsearch.repositories.CategoryRepository;
import com.example.jobsearch.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryByName(String category){
        return categoryRepository.findByName(category).orElseThrow(() -> new CategoryNotFoundException("Category by name "+category+" not found"));
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category= categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category by id "+id+" not found"));
        return getDto(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(this::getDto).collect(Collectors.toList());
    }

    private CategoryDto getDto(Category category){
        Category parentCategory = category;
        CategoryDto parentDto = null;
        if (parentCategory != null) {
            parentDto = CategoryDto.builder()
                    .id(parentCategory.getId())
                    .name(parentCategory.getName())
                    .categoryParent(null)
                    .build();
        }

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .categoryParent(parentDto)
                .build();
    }
}
