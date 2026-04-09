package com.abbtech.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "employee")
public class EmployeeConfigurationProperty {
    private String name;
    private String email;
    private String phone;
    private String[] items;

    public void setItems(String[] items) {
        this.items = items;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
