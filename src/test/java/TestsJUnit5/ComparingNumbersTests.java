package TestsJUnit5;

import org.example.Lesson14JUnut5.ComparingNumbers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparingNumbersTests {
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
    void testInvalidInput() {
        String input = "abc\n10\n5\n";
        provideInput(input);
        ComparingNumbers.main(new String[]{});
       String expectedOutput = "Введите первое число: Ошибка: введите целое число!\nВведите первое число: Введите второе число: 10 > 5";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    void testNumbersEqual() {
        String input = "5\n5\n";
        provideInput(input);
        ComparingNumbers.main(new String[]{});
        String expectedOutput = "Введите первое число: Введите второе число: Числа равны";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    void testFirstGreater() {
        String input = "10\n5\n";
        provideInput(input);
        ComparingNumbers.main(new String[]{});
        String expectedOutput = "Введите первое число: Введите второе число: 10 > 5";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    void testSecondGreater() {
        String input = "3\n7\n";
        provideInput(input);
        ComparingNumbers.main(new String[]{});
        String expectedOutput = "Введите первое число: Введите второе число: 3 < 7";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
