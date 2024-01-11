package com.example.tutorial.controller;

import com.example.tutorial.entity.Department;
import com.example.tutorial.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = DepartmentController.class)
@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {
    @MockBean
    private DepartmentService departmentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Department department;

    @BeforeEach
    void setUp() {
       department = Department.builder().departmentId(1L).departmentName("Computer Engineering").departmentCode("CE")
               .departmentAddress("Istanbul").build();
    }

    @Test
    public void shouldReturnSavedDepartment() throws Exception {
        Department inputDepartment = Department.builder().departmentName("Computer Engineering").departmentCode("CE")
                .departmentAddress("Istanbul").build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);
        mockMvc.perform(post("/department")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(inputDepartment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }

    @Test
    public void shouldReturnAllDepartments() throws Exception {
        List<Department> departments = new ArrayList<>();
        departments.add(department);
        departments.add(department);
        departments.add(department);
        Mockito.when(departmentService.getAllDepartments()).thenReturn(departments);
        mockMvc.perform(get("/department")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",Matchers.hasSize(3)));
    }

}