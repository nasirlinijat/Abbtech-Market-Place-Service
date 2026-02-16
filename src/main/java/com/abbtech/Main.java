package com.abbtech;

import com.abbtech.calculator.CalculatorService;
import com.abbtech.calculator.CalculatorServiceImpl;

import java.util.Scanner;

public class Main {
    static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number A: ");
        int a = sc.nextInt();
        System.out.println("Enter a number B: ");
        int b = sc.nextInt();
        System.out.println("Enter a calculator method: ");
        String method = sc.next();
        CalculatorService calculatorService = new CalculatorServiceImpl();

        switch (method) {
            case "add":
                System.out.println(calculatorService.add(a, b));
                break;
            case "subtract":
                System.out.println(calculatorService.sub(a, b));
                break;
            case "multiply":
                System.out.println(calculatorService.mul(a, b));
                break;
            case "divide":
                System.out.println(calculatorService.div(a, b));
                break;
            default:
                System.out.println("Invalid operation");
        }

    }
}
