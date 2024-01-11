package com.example.tutorial.service;

import com.example.tutorial.entity.Department;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> getAllDepartments();

    public Department getDepartmentById(Long id);

    public void deleteDepartmentById(Long id);

    public Department updateDepartmentById(Long id,Department department);

    public Department getDepartmentByDepartmentName(String departmentName);
}
