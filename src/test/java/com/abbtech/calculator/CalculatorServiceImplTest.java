package com.abbtech.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceImplTest {

    private CalculatorServiceImpl calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorServiceImpl();
    }

    @Test
    void addTest_success() {
        double actualResult = calculatorService.add(1, 3);
        Assertions.assertEquals(4, actualResult);
    }

    @Test
    void addTest_throw_0_is_not_allowed_for_a() {
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class,
                () -> calculatorService.add(0, 3));
        Assertions.assertEquals("0 is not allowed", exception.getMessage());
    }
}
