package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.CategoryDao;
import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.models.Category;
import com.example.jobsearch.exceptions.CategoryNotFoundException;
import com.example.jobsearch.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao dao;

    @Override
    public Category getCategoryIdByName(String category){
        try {
        return dao.getCategoryByName(category).orElseThrow(CategoryNotFoundException::new);
        }catch (CategoryNotFoundException e){
            log.error("category: {} does not exists",category);
        }
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Long id)  {
        try {
        Category category= dao.getCategoryById(id)
                .orElseThrow(
                        CategoryNotFoundException::new);

        return CategoryDto.builder()
                .name(category.getName())
                .id(category.getId())
                .categoryId(category.getId())
                .build();
        }catch (CategoryNotFoundException e){
            log.error("category with id:{} not found",id);
        }
        return null;
    }
}
