package com.example.jobsearch.exceptions;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
