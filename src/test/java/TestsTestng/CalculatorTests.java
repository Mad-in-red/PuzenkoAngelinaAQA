package TestsTestng;

import org.example.Lesson14_1.Calculator;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CalculatorTests {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeMethod
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterMethod
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testInvalidNumbersInput() {
        String input = "abc\n10\n*?%#\n5\n+\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите число!"),
                "Сообщение об ошибке для нечислового ввода не найдено");
        assertTrue(output.contains("10,00 + 5,00 = 15,00"),
                "Некорректный результат сложения");
    }

    @Test
    public void testInvalidOperationInput() {
        String input = "10\n5\nghjgkh\n+\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: допустимые операции +, -, *, /"),
                "Сообщение об ошибке операции не найдено");
        assertTrue(output.contains("10,00 + 5,00 = 15,00"),
                "Некорректный результат после ошибочной операции");
    }

    @Test
    public void testSummation() {
        String input = "5,2\n5\n+\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("5,20 + 5,00 = 10,20"),
                "Некорректный результат сложения");
    }

    @Test
    public void testSubtraction() {
        String input = "10,8\n5,3\n-\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("10,80 - 5,30 = 5,50"),
                "Некорректный результат вычитания");
    }

    @Test
    public void testMultiplication() {
        String input = "9\n-3\n*\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("9,00 * -3,00 = -27,00"),
                "Некорректный результат умножения");
    }

    @Test
    public void testDivision() {
        String input = "9\n3\n/\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("9,00 / 3,00 = 3,00"),
                "Некорректный результат деления");
    }

    @Test
    public void testDivisionByZero() {
        String input = "9\n0\n/\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("На 0 делить нельзя!"),
                "Отсутствует сообщение о делении на ноль");
    }

    @Test
    public void testBigNumbers() {
        String input = "1,0E308\n1,0E308\n*\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("Infinity"),
                "Ожидался результат Infinity для больших чисел");
    }

    @Test
    public void testInfinityInput() {
        String input = "Infinity\n5\n/\n";
        provideInput(input);

        Calculator.main(new String[]{});

        String output = outContent.toString().trim().replace("\r", "");
        assertTrue(output.contains("Infinity"),
                "Ожидался результат Infinity");
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
