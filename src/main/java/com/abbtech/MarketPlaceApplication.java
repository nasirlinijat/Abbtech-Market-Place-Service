package com.abbtech;

import com.abbtech.bean.annotation.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MarketPlaceApplication {
    static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MarketPlaceApplication.class, args);
        Employee employee = context.getBean(Employee.class);
        System.out.println(employee.firstName);
    }
}
