package com.abbtech;

import com.abbtech.scopes.ApplicationScopedClass;
import com.abbtech.scopes.SingletonScopedClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MarketPlaceApplication {
    static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MarketPlaceApplication.class, args);

        SingletonScopedClass class1 = ctx.getBean(SingletonScopedClass.class);
        SingletonScopedClass class2 = ctx.getBean(SingletonScopedClass.class);
        SingletonScopedClass class3 = ctx.getBean(SingletonScopedClass.class);
        SingletonScopedClass class4 = ctx.getBean(SingletonScopedClass.class);


        ApplicationScopedClass class5 = ctx.getBean(ApplicationScopedClass.class);
        ApplicationScopedClass class6= ctx.getBean(ApplicationScopedClass.class);
        ApplicationScopedClass class7 = ctx.getBean(ApplicationScopedClass.class);
        ApplicationScopedClass class8 = ctx.getBean(ApplicationScopedClass.class);

        System.out.println(class1);
        System.out.println(class2);
        System.out.println(class3);
        System.out.println(class4);


        System.out.println(class5);
        System.out.println(class6);
        System.out.println(class7);
        System.out.println(class8);


    }
}
