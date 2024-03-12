package com.example.jobsearch.contoller;

import com.example.jobsearch.exception.CategoryNotFoundException;
import com.example.jobsearch.services.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping("/{name}")
    public ResponseEntity<Long> getCategoryIdByName(@PathVariable String name) {
        try {
            Long categoryId = categoryService.getCategoryIdByName(name);
            return ResponseEntity.ok(categoryId);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
