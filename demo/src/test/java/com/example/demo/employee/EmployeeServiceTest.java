package com.example.demo.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService underTest;

    @BeforeEach
    void setUp(){
        underTest = new EmployeeService(employeeRepository);
    }

    @Test
    void canGetAllEmployees() {
        //when
        underTest.getEmployees();
        //then
        verify(employeeRepository).findAll();
    }

    @Test
    @Disabled
    void getEmployeeById() {
    }

    @Test
    @Disabled
    void addNewEmployee() {
    }

    @Test
    @Disabled
    void deleteEmployee() {
    }

    @Test
    @Disabled
    void updateEmployee() {
    }
}