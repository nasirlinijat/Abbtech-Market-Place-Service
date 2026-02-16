package com.abbtech.calculator;

public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public void add(int a, int b) {
        System.out.println("Adding " + a + " " + b);
    }

    @Override
    public void sub(int a, int b) {
        System.out.println("Subtracting " + a + " " + b);
    }

    @Override
    public void mul(int a, int b) {
        System.out.println("Multiplying " + a + " " + b);
    }

    @Override
    public void div(int a, int b) {
        System.out.println("Dividing " + a + " " + b);
    }
}
