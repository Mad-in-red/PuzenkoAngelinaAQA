package TestsJUnit5;

import org.example.Lesson14JUnut5.Calculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {
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
        String input = "abc\n10\n*?%#\n5\n+\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите число!"));
        assertTrue(output.contains("10,00 + 5,00 = 15,00"));
    }

    @Test
    void testInvalidOperationInput() {
        String input = "10\n5\nghjgkh\n+\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: допустимые операции +, -, *, /"));
        assertTrue(output.contains("10,00 + 5,00 = 15,00"));
    }

    @Test
    void testSummation() {//переделать на сложение
        String input = "5,2\n5\n+\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("5,20 + 5,00 = 10,20"));
    }

    @Test
    void testSubtraction() {
        String input = "10,8\n5,3\n-\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("10,80 - 5,30 = 5,50"));
    }

    @Test
    void testMultiplication() {
        String input = "9\n-3\n*\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("9,00 * -3,00 = -27,00"));
    }

    @Test
    void testDivision() {
        String input = "9\n3\n/\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("9,00 / 3,00 = 3,00"));
    }

    @Test
    void testDivisionByZero() {
        String input = "9\n0\n/\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("На 0 делить нельзя!"));
    }

    @Test
    void testBigNumbers() {
        String input = "1,0E308\n1,0E308\n*\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("Infinity"), "Должно обрабатывать очень большие числа");
    }

    @Test
    void testInfinityInput() {
        String input = "Infinity\n5\n/\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("Infinity"), "Должно обрабатывать Infinity");
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
