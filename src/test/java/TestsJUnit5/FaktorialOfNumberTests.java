package TestsJUnit5;

import org.example.Lesson14JUnut5.FactorialOfNumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaktorialOfNumberTests {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testInvalidNumbersInput() {
        String input = "abc\n-10\n5,5\n5\n";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите положительное число <= 20!"));
        assertTrue(output.contains("Факториал 5 = 120"));
    }

    @Test
    void testCalcFactorial() {
        String input = "6";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Факториал 6 = 720"));
    }

    @Test
    void testMaxFactorialForLong() {
        String input = "21\n20\n";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите положительное число <= 20!"));
        assertTrue(output.contains("Факториал 20 = 2432902008176640000"));
    }

    @Test
    void testFactorialOfZero() {
        String input = "0\n1\n";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите положительное число <= 20!"));
        assertTrue(output.contains("Факториал 1 = 1"));
    }

    @Test
    void testFactorialOfOne() {
        String input = "1";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Факториал 1 = 1"));
    }

    private void provideInput(String data) {
            ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
            System.setIn(testIn);
    }
}
