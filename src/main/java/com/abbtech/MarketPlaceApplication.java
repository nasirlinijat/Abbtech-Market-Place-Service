package com.abbtech;

import com.abbtech.bean.annotation.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MarketPlaceApplication {
    static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MarketPlaceApplication.class, args);
        Employee employee = (Employee) context.getBean("ali");
        Employee employeeVali = (Employee) context.getBean("vali");
        System.out.println(employee);


//        Map<String, Employee> employees = context.getBeansOfType(Employee.class);
//
//        System.out.println(employee);
//
//        ProductController controller = context.getBean(ProductController.class);
//        var products = controller.getAllProducts();
//        System.out.println(products);
//
//        controller.setEmployee(employee);

    }
}
