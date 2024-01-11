package com.example.tutorial.exception;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
