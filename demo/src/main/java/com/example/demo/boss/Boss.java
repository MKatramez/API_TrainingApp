package com.example.demo.boss;

import com.example.demo.employee.Employee;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Boss {
    @Id
    @SequenceGenerator(
            name = "boss_sequence",
            sequenceName = "boss_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "boss_sequence"
    )
    private Long id;
    private String name;
    private String email;
    @Transient
    private ArrayList<Employee> employees;


    public Boss(String name, String email, ArrayList<Employee> employees) {
        this.name = name;
        this.email = email;
        this.employees = employees;
    }
}
