package com.example.tutorial.repository;

import com.example.tutorial.entity.Department;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
         Department department = Department.builder().departmentName("Computer Engineering").departmentCode("CSE")
                .departmentAddress("Istanbul").build();
        departmentRepository.save(department);
    }

    @Test
    public void shouldFindDepartmentByName(){
        Department department = departmentRepository.findByDepartmentNameIgnoreCase("Computer Engineering");
        Assertions.assertThat(department).isNotNull();
        Assertions.assertThat(department.getDepartmentName()).isEqualTo("Computer Engineering");
    }
}