package com.example.demo.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class EmployeeConfig {

    private static final Logger log = LoggerFactory.getLogger(EmployeeConfig.class);

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
            Employee employee3 = new Employee(
                    "Mike",
                    "mike@gmail.com",
                    LocalDate.of(1996, Month.JANUARY, 28)
            );

            repository.saveAll(
                    List.of(employee1, employee2)

            );
            log.info("this is message using log: save "+ repository.save(employee3));

        };
    }

}
