package com.abbtech.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceImplTest {

    private CalculatorServiceImpl calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorServiceImpl();
    }

    @Test
    void addTest_success() {
        int actualResult = calculatorService.add(1, 2);
        Assertions.assertEquals(3, actualResult);
    }

    @Test
    void addTest_throw_0_is_not_allowed_for_a() {
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class,
                () -> calculatorService.add(0, 2));
        Assertions.assertEquals("0 is not allowed", exception.getMessage());
    }

    @Test
    void addTest_throw_b_is_not_allowed_to_negative_number() {
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class,
                () -> calculatorService.add(1, -5));
        Assertions.assertEquals("b is not allowed to negative number", exception.getMessage());
    }


    private static Stream<Integer> addTestThrowBSource() {
        List<Integer> list = new ArrayList<>();
        for (int i = 10; i < 50; i++) {
            list.add(i);
        }
        return list.stream();
    }

    @ParameterizedTest
    @MethodSource("addTestThrowBSource")
    void addTest_throw_b(int b) {
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class,
                () -> calculatorService.add(1, b));
        Assertions.assertEquals("greater than 10 and 50 is not allowed", exception.getMessage());
    }

}
