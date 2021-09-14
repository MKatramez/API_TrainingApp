package com.example.demo.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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
    void CanGetEmployeeById() {
    }

    @Test
    void canAddNewEmployee() {
        //given
        String email = "mohamad@gmail.com";
        Employee employee = new Employee(
                "Mohamad",
                email,
                LocalDate.of(1998, Month.JANUARY, 28)
        );
        //when
        underTest.addNewEmployee(employee);

        //then
        ArgumentCaptor<Employee> employeeArgumentCaptor =
                ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository)
                .save(employeeArgumentCaptor.capture());

        // capturedEmployee is employee from the EmployeeService Class
        // and employee is our given employee heer wish underTest received
        Employee capturedEmployee = employeeArgumentCaptor.getValue();

        assertThat(capturedEmployee).isEqualTo(employee);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        String email = "mohamad@gmail.com";
        Employee employee = new Employee(
                "Mohamad",
                email,
                LocalDate.of(1998, Month.JANUARY, 28)
        );

        given(employeeRepository.findEmployeeByEmail(anyString()))
                .willReturn(Optional.of(employee));
        //when
        //then
        assertThatThrownBy(() ->
                underTest.addNewEmployee(employee))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email taken");


        verify(employeeRepository, never()).save(any());

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