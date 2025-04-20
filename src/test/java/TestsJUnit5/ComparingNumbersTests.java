package TestsJUnit5;

import org.example.Lesson14_JUnit.MathCalculations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparingNumbersTests {
    @Test
    void testComparingNumbersEqual() {
        String result = MathCalculations.comparingNumbers(5, 5);
        assertEquals("Числа равны", result);
    }

    @Test
    void testComparingNumbersGreater() {
        String result = MathCalculations.comparingNumbers(8, 5);
        assertEquals("8 > 5", result);
    }

    @Test
    void testComparingNumbersLess() {
        String result = MathCalculations.comparingNumbers(3, 5);
        assertEquals("3 < 5", result);
    }
}
