package com.example.demo.employee;

import com.example.demo.boss.Boss;
import com.example.demo.boss.BossRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner (EmployeeRepository repository){
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

            repository.saveAll(
                    List.of(employee1, employee2)

            );
        };
    }

}
