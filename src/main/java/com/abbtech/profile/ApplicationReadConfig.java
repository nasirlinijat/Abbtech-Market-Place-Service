package com.abbtech.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationReadConfig {

    private final Environment environment;

    @Value("${employee.name}")
    private String employeeName;

    @Value("${employee.items[0]}")
    private String items;

    @Value("${employee.email}")
    private String employeeEmail;

    @Value("${employee.phone}")
    private String employeePhone;

    @Value("${employee.age}")
    private Integer age;

    public ApplicationReadConfig(Environment environment) {
        this.environment = environment;
    }


    @Bean("employeeEnvironment")
    public Employee employeeEnvironment() {
        String employeeName = environment.getProperty("employee.name");
        String employeeEmail = environment.getProperty("employee.email");
        String employeePhone = environment.getProperty("employee.phone");

        return new Employee(employeeName, employeeEmail, employeePhone);
    }


    @Bean
    public Employee employeeValueAsBean() {
        return new Employee(employeeName, employeeEmail, employeePhone);
    }
}
