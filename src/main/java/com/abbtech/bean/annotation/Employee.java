package com.abbtech.bean.annotation;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    public String firstName = "Employee name";
    public String lastName = "Employee last name";

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(){
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
