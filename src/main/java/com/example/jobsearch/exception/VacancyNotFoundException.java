package com.example.jobsearch.exception;

public class VacancyNotFoundException extends Exception{
    public VacancyNotFoundException() {
    }

    public VacancyNotFoundException(String message) {
        super(message);
    }
}
