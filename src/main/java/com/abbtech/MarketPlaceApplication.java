package com.abbtech;

import com.abbtech.profile.Employee;
import com.abbtech.profile.EmployeeConfigurationProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MarketPlaceApplication {
    static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MarketPlaceApplication.class, args);
        Employee employee = ctx.getBean("employeeValueAsBean", Employee.class);
        EmployeeConfigurationProperty employeeConfigurationProperty = ctx.getBean(EmployeeConfigurationProperty.class);
        System.out.println(employeeConfigurationProperty);
        System.out.println(employee);
    }
}
