package TestsTestng;


import org.example.Lesson14_1.ComparingNumbers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.testng.Assert.*;

public class ComparingNumbersTests {
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
    public void testInvalidInput() {
        String input = "abc\n10\n5\n";
        provideInput(input);
        ComparingNumbers.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите целое число!"),
                "Выводится сообщение об ошибке");
        assertTrue(output.contains("10 > 5"),
                "Выводится корректный результат сравнения");
    }

    @Test
    public void testNumbersEqual() {
        String input = "5\n5\n";
        provideInput(input);
        ComparingNumbers.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Числа равны"),
                "Выводится сообщение о равенстве");
    }

    @Test
    public void testFirstGreater() {
        String input = "10\n5\n";
        provideInput(input);
        ComparingNumbers.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("10 > 5"),
                "Выводится корректный результат сравнения");
    }

    @Test
    public void testSecondGreater() {
        String input = "3\n7\n";
        provideInput(input);
        ComparingNumbers.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("3 < 7"),
                "Выводится корректный результат сравнения");
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
