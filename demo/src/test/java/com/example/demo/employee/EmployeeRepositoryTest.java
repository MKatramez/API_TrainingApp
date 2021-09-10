package com.example.demo.employee;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository underTest;


    @Test
    @Disabled
    void itShouldCheckIfEmployeeFoundByEmail() {
        //given
        String email = "mohamad@gmail.com";
        Employee employee = new Employee(
                "Mohamad",
                email,
                LocalDate.of(1998, Month.JANUARY, 28)
        );
        underTest.findEmployeeByEmail(employee.getEmail());
        //when
        Optional<Employee> expected = underTest.findEmployeeByEmail(email);
        //then
        assertThat(expected).isEqualTo(employee);
    }
}