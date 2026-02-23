package com.abbtech.controller;

import com.abbtech.service.CalculatorService;
import com.abbtech.service.CalculatorServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CalculatorController", urlPatterns = "/calculator")
public class CalculatorController extends HttpServlet {
    private CalculatorService calculatorService;

    @Override
    public void init() throws ServletException {
        log("CalculatorController init");
        calculatorService = new CalculatorServiceImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));
        String method = request.getParameter("method");
        int result = 0;
        boolean isSuccess = true;
        switch (method) {
            case "add":
                try {
                    result = calculatorService.add(a, b);
                } catch (ArithmeticException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().println(e.getMessage());
                    isSuccess = false;
                }
                break;
            case "subtract":
                result = calculatorService.sub(a, b);
                break;
            case "multiply":
                result = calculatorService.mul(a, b);
                break;
            case "divide":
                result = calculatorService.div(a, b);
                break;
        }
        if (isSuccess) {
            PrintWriter writer = response.getWriter();
            writer.println("result x: " + result);
        }
    }
}
