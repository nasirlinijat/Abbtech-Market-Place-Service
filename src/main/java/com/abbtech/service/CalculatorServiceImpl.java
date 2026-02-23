package com.abbtech.service;

public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public int add(int a, int b) {
        if (a == 0) {
            throw new ArithmeticException("0 is not allowed");
        }
        if (b < 0) {
            throw new ArithmeticException("b is not allowed to negative number");
        }

        if (b >= 10 && b < 50) {
            throw new ArithmeticException("greater than 10 and 50 is not allowed");
        }

        System.out.println("Adding " + a + " " + b);
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        System.out.println("Subtracting " + a + " " + b);
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        System.out.println("Multiplying " + a + " " + b);
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        System.out.println("Dividing " + a + " " + b);
        return a / b;
    }

    @Override
    public void printNumbers(int a, int b) {
        System.out.println("Printing " + a + " " + b);
    }
}
