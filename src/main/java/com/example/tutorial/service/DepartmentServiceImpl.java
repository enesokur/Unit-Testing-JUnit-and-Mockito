package com.example.tutorial.service;

import com.example.tutorial.entity.Department;
import com.example.tutorial.exception.DepartmentNotFoundException;
import com.example.tutorial.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department with id "+ id + " not found");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartmentById(Long id,Department department) {
        Department departmentFromDb = departmentRepository.findById(id).get();
        if(department.getDepartmentName() != null && department.getDepartmentName() != ""){
            departmentFromDb.setDepartmentName(department.getDepartmentName());
        }
        if(department.getDepartmentAddress() != null && department.getDepartmentAddress() != ""){
            departmentFromDb.setDepartmentAddress(department.getDepartmentAddress());
        }
        if(department.getDepartmentCode() != null && department.getDepartmentCode() != ""){
            departmentFromDb.setDepartmentCode(department.getDepartmentCode());
        }
        return departmentRepository.save(departmentFromDb);
    }

    @Override
    public Department getDepartmentByDepartmentName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
