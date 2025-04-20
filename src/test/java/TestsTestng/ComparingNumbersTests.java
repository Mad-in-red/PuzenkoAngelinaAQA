package TestsTestng;

import org.example.Lesson14_Testng.MathCalculations;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ComparingNumbersTests {

    @Test
    public void testComparingNumbersEqual() {
        String result = MathCalculations.comparingNumbers(5, 5);
        assertEquals(result, "Числа равны");
    }

    @Test
    public void testComparingNumbersGreater() {
        String result = MathCalculations.comparingNumbers(8, 5);
        assertEquals(result, "8 > 5");
    }

    @Test
    public void testComparingNumbersLess() {
        String result = MathCalculations.comparingNumbers(3, 5);
        assertEquals(result, "3 < 5");
    }

}
