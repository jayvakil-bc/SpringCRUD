package com.example.demo.Student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository){
        return args ->{
            Student Jay = new Student(
                    1L,
                    "Jay",
                    "jayvakil50@gmail.com",
                    LocalDate.of(2000, Month.OCTOBER, 4)
            );

            Student Vakil = new Student(
                    2L,
                    "Vakil",
                    "vijaykumar@gmail.com",
                    LocalDate.of(2010, Month.OCTOBER, 7)
            );

            repository.saveAll(
                    List.of(Jay,Vakil)
            );
        };
    }
}
