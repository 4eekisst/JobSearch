package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.CategoryDao;
import com.example.jobsearch.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl {
    private final CategoryDao categoryDao;

    public Long getCategoryIdByName(String name) throws CategoryNotFoundException {
        return categoryDao.getCategory(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category: " + name + " not found"))
                .getId();
    }
}

