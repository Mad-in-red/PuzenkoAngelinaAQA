package TestsJUnit5;

import org.example.Lesson14_JUnit.MathCalculations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FaktorialOfNumberTests {

    @Test
    void testFactorialOfZero() {
        long result = MathCalculations.factorial(0);
        assertEquals(1L, result, "Факториал 0 должен быть равен 1");
    }

    @Test
    void testFactorialOfOne() {
        long result = MathCalculations.factorial(1);
        assertEquals(1L, result, "Факториал 1 должен быть равен 1");
    }

    @Test
    void testFactorialOfSix() {
        long result = MathCalculations.factorial(6);
        assertEquals(720L, result, "Факториал 6 должен быть равен 720");
    }

    @Test
    void testFactorialOfTwenty() {
        long result = MathCalculations.factorial(20);
        assertEquals(2432902008176640000L, result,
                "Факториал 20 должен быть равен 2432902008176640000");
    }

    @Test
    void testNegativeNumber() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> MathCalculations.factorial(-1),
                "Ожидалось исключение для отрицательного числа"
        );
        assertEquals("Факториал определен только для n >= 0", exception.getMessage());
    }

    @Test
    void testFactorialOverflow() {
        assertThrows(
                ArithmeticException.class,
                () -> MathCalculations.factorial(21),
                "Ожидалось исключение при переполнении long"
        );
    }
}
