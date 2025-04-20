package TestsTestng;

import org.example.Lesson14_Testng.MathCalculations;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FaktorialOfNumberTests {

    @Test
    public void testFactorialOfZero() {
        long result = MathCalculations.factorial(0);
        assertEquals(result, 1L, "Факториал 0 должен быть равен 1");
    }

    @Test
    public void testFactorialOfOne() {
        long result = MathCalculations.factorial(1);
        assertEquals(result, 1L, "Факториал 1 должен быть равен 1");
    }

    @Test
    public void testFactorialOfSix() {
        long result = MathCalculations.factorial(6);
        assertEquals(result, 720L, "Факториал 6 должен быть равен 720");
    }

    @Test
    public void testFactorialOfTwenty() {
        long result = MathCalculations.factorial(20);
        assertEquals(result, 2432902008176640000L,
                "Факториал 20 должен быть равен 2432902008176640000");
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Факториал определен только для n >= 0")
    public void testNegativeNumber() {
        MathCalculations.factorial(-1);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testFactorialOverflow() {
        MathCalculations.factorial(21);
    }

}