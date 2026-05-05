package com.abbtech.aspect;

import com.abbtech.annotation.CustomTransactionAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionManagementAspect {

    @Pointcut("@annotation(com.abbtech.annotation.CustomTransactionAnnotation)")
    public void customTransaction() {}

    @Before("customTransaction()")
    public void openConnection(JoinPoint jp) {
        System.out.println("[BEFORE] Opening JDBC connection for: " + jp.getSignature().getName());
    }

    @After("customTransaction()")
    public void closeConnection(JoinPoint jp) {
        System.out.println("[AFTER] Releasing JDBC connection for: " + jp.getSignature().getName());
    }

    @AfterReturning(value = "customTransaction()", returning = "result")
    public void afterSuccess(JoinPoint jp, Object result) {
        System.out.println("[AFTER-RETURNING] " + jp.getSignature().getName() + " succeeded, result: " + result);
    }

    @AfterThrowing(value = "customTransaction()", throwing = "ex")
    public void rollbackOnException(JoinPoint jp, Throwable ex) {
        System.out.println("[AFTER-THROWING] Rollback for: " + jp.getSignature().getName() + " — " + ex.getMessage());
    }

    @Around("customTransaction()")
    public Object manageTransaction(ProceedingJoinPoint jp) throws Throwable {
        var sig = (MethodSignature) jp.getSignature();
        var method = sig.getMethod();
        var annotation = method.getAnnotation(CustomTransactionAnnotation.class);
        var args = jp.getArgs();

        System.out.println("[AROUND] Method: " + method.getName());

        if (annotation != null)
            System.out.println("[AROUND] ReadOnly: " + annotation.readOnlyTrue());

        var paramNames = sig.getParameterNames();
        var paramTypes = method.getParameterTypes();
        for (int i = 0; i < args.length; i++)
            System.out.printf("[AROUND] Param[%s] %s = %s%n", paramTypes[i].getSimpleName(), paramNames[i], args[i]);

        System.out.println("[AROUND] Start transaction");
        try {
            Object result = jp.proceed();
            System.out.println("[AROUND] Return type: " + method.getReturnType().getSimpleName());
            System.out.println("[AROUND] Return value: " + result);
            System.out.println("[AROUND] Commit transaction");
            return result;
        } catch (Throwable ex) {
            System.out.println("[AROUND] Rollback transaction");
            throw ex;
        }
    }
}
