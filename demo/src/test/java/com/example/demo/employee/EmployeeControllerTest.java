package com.example.demo.employee;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest for testing MVC controllers without starting a full HTTP server.
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService underTest;

    @Test
    void whenGetEmployees_thenReturnJsonArray() throws Exception {
        //given
        Employee employee1 = new Employee(
                "Mohamad",
                "mohamad@gmail.com",
                LocalDate.of(1998, Month.JANUARY, 28)
        );
        Employee employee2 = new Employee(
                "Alex",
                "alex@gmail.com",
                LocalDate.of(1990, Month.JANUARY, 28)
        );
        List<Employee> allEmployees = Arrays.asList(employee1, employee2);

        given(underTest.getEmployees()).willReturn(allEmployees);
        //when
        //then
        mockMvc.perform(get("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(employee1.getName())))
                .andExpect(jsonPath("$[1].name", is(employee2.getName())));

    }
    @Test
    void whenGetEmployeeById_thenReturnJsonArray() throws Exception {
        //given
        Long id = 1L;
        Employee employee = new Employee(
                id,
                "Mohamad",
                "mohamad@gmail.com",
                LocalDate.of(1998, Month.JANUARY, 28)
        );


        given(underTest.getEmployeeById(id)).willReturn(Optional.of(employee));
        //when
        //then
        mockMvc.perform(get("/api/v1/employee/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(employee.getName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));


    }

    @Test
    @Disabled
    void registerNewEmployee_thenReturnJsonArray() {
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