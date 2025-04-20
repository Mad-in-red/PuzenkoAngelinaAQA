package TestsJUnit5;

import org.example.Lesson14_JUnit.MathCalculations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {

    @Test
    void testSummation() {
        double result = MathCalculations.doOperation(5.2, 5, "+");
        assertEquals(10.2, result, 0.001, "Некорректный результат сложения");
    }

    @Test
    void testSubtraction() {
        double result = MathCalculations.doOperation(10.8, 5.3, "-");
        assertEquals(5.5, result, 0.001, "Некорректный результат вычитания");
    }

    @Test
    void testMultiplication() {
        double result = MathCalculations.doOperation(9, -3, "*");
        assertEquals(-27.0, result, 0.001, "Некорректный результат умножения");
    }

    @Test
    void testDivision() {
        double result = MathCalculations.doOperation(9, 3, "/");
        assertEquals(3.0, result, 0.001, "Некорректный результат деления");
    }

    @Test
    void testDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class,
                () -> MathCalculations.doOperation(9, 0, "/"),
                "Ожидалось исключение при делении на ноль");

        assertEquals("На 0 делить нельзя!", exception.getMessage());
    }

    @Test
    void testInvalidOperation() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MathCalculations.doOperation(5, 5, "%"),
                "Ожидалось исключение при неверной операции");

        assertEquals("Неизвестная операция: %", exception.getMessage());
    }

    @Test
    void testBigNumbersMultiplication() {
        double result = MathCalculations.doOperation(1.0E308, 2, "*");
        assertEquals(Double.POSITIVE_INFINITY, result, "Ожидался результат Infinity");
    }

    @Test
    void testInfinityDivision() {
        double result = MathCalculations.doOperation(Double.POSITIVE_INFINITY, 5, "/");
        assertEquals(Double.POSITIVE_INFINITY, result, "Ожидался результат Infinity");
    }

}
