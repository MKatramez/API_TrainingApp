package com.example.demo.employee;

import com.example.demo.employee.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService underTest;

    @BeforeEach
    void setUp() {
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
    void canGetEmployeeById() {
        //given
        String email = "mohamad@gmail.com";
        Employee employee = new Employee(
                1L,
                "Mohamad",
                email,
                LocalDate.of(1998, Month.JANUARY, 28)
        );
        given(employeeRepository.findById(employee.getId()))
                .willReturn(Optional.of(employee));
        //when
        underTest.getEmployeeById(employee.getId());

        //then
        verify(employeeRepository).findById(employee.getId());

    }

    @Test
    void canNotGetEmployeeByIdBecauseIdDoesntExistOrTaken() {
        //given
        Long id = 1L;

        given(employeeRepository.findById(id))
                .willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() ->
                underTest.getEmployeeById(id))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("Could not find employee " + id);


        //ask about the VerificationMode if it's never
        verify(employeeRepository).findById(any());

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
        //given here change the if value in EmployeeService, so we can go inside this if
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
    void canDeleteEmployee() {
        //given
        Long id = 1L;
        given(employeeRepository.existsById(id))
                .willReturn(true);

        //when
        underTest.deleteEmployee(id);

        //then
        verify(employeeRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeleteEmployeeNotFound() {
        //given
        Long id = 1L;
        given(employeeRepository.existsById(id))
                .willReturn(false);

        //when
        //then
        assertThatThrownBy(() ->
                underTest.deleteEmployee(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("employee with id " + id + " does not exists");


        verify(employeeRepository, never()).deleteById(any());
    }


    //######################################################################
    @Test

    void canUpdateEmployee() {
        //given
        Long id = 1L;
        String email = "mohamad@gmail.com";
        Employee employee = new Employee(
                id,
                "Mohamad",
                email,
               null
        );
        Long id2 = 1L;
        String email2 = "mo@gmail.com";
        Employee employee2 = new Employee(
                id2,
                "Mo",
                email2,
                null
        );
        given(employeeRepository.findById(id))
                .willReturn(Optional.of(employee));
        //when
        underTest.updateEmployee(id, "Mo", "mo@gmail.com");
        assertThat(employee.getName()).isEqualTo(employee2.getName());

        //then
        verify(employeeRepository).findById(id);

    }
    @Test
    void willThrowWhenUpdateEmployeeEmailIsTaken() {
        //given
        Long id = 1L;
        String email = "mos@gmail.com";
        Employee employee = new Employee(
                id,
                "Mos",
                email,
                LocalDate.of(1998, Month.JANUARY, 28)
        );

        Long id2 = 1L;
        String email2 = "mo@gmail.com";
        Employee employee2 = new Employee(
                id2,
                "Mo",
                email2,
                null
        );

        given(employeeRepository.findById(id2))
                .willReturn(Optional.of(employee2));
        given(employeeRepository.findEmployeeByEmail(anyString()))
                .willReturn(Optional.of(employee2));
        //when
        //then
        assertThatThrownBy(() ->
                underTest.updateEmployee(id, employee.getName(), email))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email taken");

        verify(employeeRepository).findById(id2);

    }
}

