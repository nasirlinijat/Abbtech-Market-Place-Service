package com.abbtech.calculator;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public double add(double a, double b) {
        if (a ==0){
            throw new ArithmeticException("0 is not allowed");
        }
        return a + b;
    }

    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    @Override
    public double divide(double a, double b) {
        return a / b;
    }
}
