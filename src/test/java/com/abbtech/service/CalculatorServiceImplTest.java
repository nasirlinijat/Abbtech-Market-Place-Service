package com.abbtech.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(3, actualResult);
    }

    @Test
    void addTest_throw_0_is_not_allowed_for_a() {
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class,
                () -> calculatorService.add(0, 2));
        assertEquals("0 is not allowed", exception.getMessage());
    }

    @Test
    void addTest_throw_b_is_not_allowed_to_negative_number() {
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class,
                () -> calculatorService.add(1, -5));
        assertEquals("b is not allowed to negative number", exception.getMessage());
    }

    @Test
    void printNumbersTest_success() {
        calculatorService.printNumbers(1, -5);
        Assertions.assertDoesNotThrow(() -> calculatorService.printNumbers(1, -5));
    }


    private static Stream<Integer> addTestThrowBSource() {
        List<Integer> list = new ArrayList<>();
        for (int i = 10; i < 50; i++) {
            list.add(i);
        }
        return list.stream();
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(null, "true", 1),
                Arguments.of("", "true", 2),
                Arguments.of("  ", "true", 3),
                Arguments.of("not blank", "false", 4)
        );
    }

    @ParameterizedTest
    @MethodSource("addTestThrowBSource")
    void addTest_throw_b(int b) {
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class,
                () -> calculatorService.add(1, b));
        assertEquals("greater than 10 and 50 is not allowed", exception.getMessage());
    }


    @ParameterizedTest
    @CsvSource(value = {"test,TEST,", "tEst,TEST,2", "Java,JAVA,3"}, delimiter = ',')
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected, Integer a) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

}
