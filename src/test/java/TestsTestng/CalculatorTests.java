package TestsTestng;

import org.example.Lesson14_Testng.MathCalculations;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class CalculatorTests {

    @Test
    public void testSummation() {
        double result = MathCalculations.doOperation(5.2, 5, "+");
        assertEquals(result, 10.2, 0.001, "Некорректный результат сложения");
    }

    @Test
    public void testSubtraction() {
        double result = MathCalculations.doOperation(10.8, 5.3, "-");
        assertEquals(result, 5.5, 0.001, "Некорректный результат вычитания");
    }

    @Test
    public void testMultiplication() {
        double result = MathCalculations.doOperation(9, -3, "*");
        assertEquals(result, -27.0, 0.001, "Некорректный результат умножения");
    }

    @Test
    public void testDivision() {
        double result = MathCalculations.doOperation(9, 3, "/");
        assertEquals(result, 3.0, 0.001, "Некорректный результат деления");
    }

    @Test(expectedExceptions = ArithmeticException.class,
            expectedExceptionsMessageRegExp = "На 0 делить нельзя!")
    public void testDivisionByZero() {
        MathCalculations.doOperation(9, 0, "/");
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Неизвестная операция: %")
    public void testInvalidOperation() {
        MathCalculations.doOperation(5, 5, "%");
    }

    @Test
    public void testBigNumbersMultiplication() {
        double result = MathCalculations.doOperation(1.0E308, 2, "*");
        assertEquals(result, Double.POSITIVE_INFINITY, "Ожидался результат Infinity");
    }

    @Test
    public void testInfinityDivision() {
        double result = MathCalculations.doOperation(Double.POSITIVE_INFINITY, 5, "/");
        assertEquals(result, Double.POSITIVE_INFINITY, "Ожидался результат Infinity");
    }

}
