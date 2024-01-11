package com.example.tutorial.controller;

import com.example.tutorial.entity.Department;
import com.example.tutorial.service.DepartmentService;
import com.example.tutorial.service.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;
    private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }
    @PostMapping("/department")
    public Department saveDepartment(@Valid @RequestBody Department department){
        logger.info("Inside saveDepartment function in DepartmentController");
        return departmentService.saveDepartment((department));
    }

    @GetMapping("/department")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("department/{id}")
    public Department getDepartmentById(@PathVariable("id") Long id){
        return departmentService.getDepartmentById(id);
    }

    @DeleteMapping("/department/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long id){
        departmentService.deleteDepartmentById(id);
        return "Department with id " + id +" deleted";
    }

    @PutMapping("/department/{id}")
    public Department updateDepartmentById(@PathVariable Long id, @RequestBody Department department){
        return departmentService.updateDepartmentById(id,department);
    }

    @GetMapping("/department/name/{departmentName}")
    public Department getDepartmentByDepartmentName(@PathVariable("departmentName") String departmentName){
        return departmentService.getDepartmentByDepartmentName(departmentName);
    }
}
