package com.example.demo.boss;

import com.example.demo.employee.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BossConfig {

    //@Bean
    CommandLineRunner commandLineRunner(BossRepository repository) {

        return args -> {
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
            ArrayList<Employee> employees = new ArrayList<Employee>();
            employees.add(employee1);
            employees.add(employee2);

            Boss b1 = new Boss(
                    "Mike",
                    "mike@gmail.com",
                    employees
            );
            repository.saveAll(
                    List.of(b1)
            );
        };
    }
}