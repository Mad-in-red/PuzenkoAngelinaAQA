package TestsTestng;

import org.example.Lesson14.FactorialOfNumber;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.testng.Assert.assertTrue;

public class FaktorialOfNumberTests {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;
    private  final PrintStream originalErr = System.err;

    @BeforeMethod
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent));
    }

    @AfterMethod
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testInvalidNumbersInput() {
        String input = "abc\n-10\n5,5\n5\n";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите положительное число <= 20!"),
                "Выводится сообщение об ошибке");
        assertTrue(output.contains("Факториал 5 = 120"),
                "Выводится факториал числа");
    }

    @Test
    public void testCalcFactorial() {
        String input = "6";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Факториал 6 = 720"),
                "Выводится факториал числа");
    }

    @Test
    void testMaxFactorialForLong() {
        String input = "21\n20\n";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите положительное число <= 20!"),
                "Выводится сообщение об ошибке");
        assertTrue(output.contains("Факториал 20 = 2432902008176640000"),
                "Выводится факториал числа");
    }

    @Test
    public void testFactorialOfZero() {
        String input = "0\n1\n";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите положительное число <= 20!"),
                "Выводится сообщение об ошибке");
        assertTrue(output.contains("Факториал 1 = 1"),
                "Выводится факториал числа");
    }

    @Test
    public void testFactorialOfOne() {
        String input = "1";
        provideInput(input);
        FactorialOfNumber.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Факториал 1 = 1"),
                "Выводится факториал числа");
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}