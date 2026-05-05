package com.abbtech.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomTransactionAnnotation {
    boolean readOnlyTrue() default false;
}
