package com.abbtech;

import com.abbtech.calculator.CalculatorService;
import com.abbtech.calculator.CalculatorServiceImpl;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number A: ");
        double a = scanner.nextDouble();
        System.out.println("Enter number B: ");
        double b = scanner.nextDouble();
        System.out.println("Enter an operation: ");
        String operation = scanner.next().toLowerCase();

        CalculatorService calculatorService = new CalculatorServiceImpl();

        switch (operation) {
            case "add":
                System.out.println(calculatorService.add(a, b));
                break;
            case "subtract":
                System.out.println(calculatorService.subtract(a, b));
                break;
            case "divide":
                System.out.println(calculatorService.divide(a, b));
                break;
            case "multiply":
                System.out.println(calculatorService.multiply(a, b));
                break;
            default:
                System.out.println("Invalid Operation");
        }
    }
}
