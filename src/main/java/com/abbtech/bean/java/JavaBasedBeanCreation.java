package com.abbtech.bean.java;

import com.abbtech.bean.annotation.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaBasedBeanCreation {

    @Bean
    public Employee ali() {
        return new Employee("Ali", "Ali Surname");
    }

    @Bean
    public Employee vali() {
        return new Employee("Vali", "Vali Surname");
    }

    @Bean
    public Employee pirvali() {
        return new Employee("Pirvali", "Pirvali Surname");
    }

}
