package com.example.jobsearch.exceptions;

public class AlreadyExistsException extends IllegalArgumentException{
    public AlreadyExistsException() {
    }

    public AlreadyExistsException(String s) {
        super(s);
    }
}
