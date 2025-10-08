package tiendat.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

//    @Test
//    void testAddition() {
//        assertEquals(5, Calculator.add(2, 3));
//    }

    @Test
    void testDivide() {
        assertEquals(2, Calculator.divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> Calculator.divide(10, 0));
        assertEquals("Cannot divide by zero", e.getMessage());
    }

    @ParameterizedTest(name = "Test {index} => {0} * {1} = {2}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void testMultiplyFromFile(int a, int b, int expected) {
        int result = Calculator.multiply(a, b);
        assertEquals(expected, result, () -> a + " * " + b + " should be " + expected);
    }


        static Calculator calculator;

        @BeforeAll
        static void initAll() {
            calculator = new Calculator();
        }

        @AfterAll
        static void cleanupAll() {
            calculator = null;
        }

        //Calculator calculator = new Calculator();
        @DisplayName("Kiểm tra phép cộng với hai số dương")
        @Test
        void testAddition() {
            assertEquals(5, calculator.add(2, 3), "Addition should return 5");
        }


}
