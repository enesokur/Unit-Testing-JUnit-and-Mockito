package com.example.tutorial.service;

import com.example.tutorial.entity.Department;
import com.example.tutorial.repository.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    private Department department;

    @BeforeEach
    public void setUp(){
        department = Department.builder().departmentId(1L)
                .departmentName("IT")
                .departmentCode("IT-06")
                .departmentAddress("Ankara").build();
    }
    
    @Test
    void shouldCreateDepartmentReturnCreatedDepartment(){
        Mockito.when(departmentRepository.save(department)).thenReturn(department);

        Department saved = departmentService.saveDepartment(department);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getDepartmentName()).isEqualTo(department.getDepartmentName());
    }

    @Test
    void shouldGetAllDepartments(){
        List<Department> departments = new ArrayList<>();
        departments.add(department);
        departments.add(department);
        departments.add(department);
        Mockito.when(departmentRepository.findAll()).thenReturn(departments);
        List<Department> foundDepartments = departmentService.getAllDepartments();
        Assertions.assertThat(foundDepartments).isNotNull();
        Assertions.assertThat(foundDepartments.size()).isEqualTo(3);
    }

    @Test
    void shouldGetDepartmentById(){
        Long departmentId = department.getDepartmentId();
        Mockito.when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        Department departmentFound = departmentService.getDepartmentById(departmentId);
        Assertions.assertThat(departmentFound).isNotNull();
        Assertions.assertThat(departmentFound.getDepartmentId()).isEqualTo(departmentId);
    }

    @Test
    void shouldDeleteDepartmentById(){
        departmentService.deleteDepartmentById(department.getDepartmentId());
        Mockito.verify(departmentRepository).deleteById(department.getDepartmentId());
    }

    @Test
    void shouldUpdateDepartmentById(){
        Long departmentId = department.getDepartmentId();
        Mockito.when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        Department department2 = Department.builder().departmentName("CE").build();
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        Department updatedDepartment = departmentService.updateDepartmentById(departmentId,department2);
        Assertions.assertThat(updatedDepartment).isNotNull();
        Assertions.assertThat(updatedDepartment.getDepartmentName()).isEqualTo("CE");
    }

    @Test
    void shouldGetDepartmentByDepartmentName() {
        String departmentName = "IT";
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase(departmentName)).thenReturn(department);
        Department found = departmentService.getDepartmentByDepartmentName(departmentName);
        Assertions.assertThat(found).isNotNull();
        Assertions.assertThat(found.getDepartmentName()).isEqualTo(departmentName);
    }

}