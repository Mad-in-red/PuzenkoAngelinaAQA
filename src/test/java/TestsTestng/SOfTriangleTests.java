package TestsTestng;

import org.example.Lesson14_1.SOfTriangle;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.testng.Assert.assertTrue;

public class SOfTriangleTests {
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
        String input = "abc\n-10\n0\n5,5\n27\n5,5\n";
        provideInput(input);
        SOfTriangle.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите положительное число!"),
                "Выводится сообщение об ошибке");
        assertTrue(output.contains("Треугольник с такими сторонами не существует!"),
                "Выводится сообщение об ошибке");
    }

    @Test
    public void testCalcArea() {
        String input = "5,5\n5,5\n6";
        provideInput(input);
        SOfTriangle.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Площадь треугольника с этими сторонами равна 13,83"),
                "Выводится S треугольника");
    }

    @Test
    public void testBigNumbers() {
        String input = "1000000\n1000000\n1414213,56\n";
        provideInput(input);
        SOfTriangle.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Площадь треугольника с этими сторонами равна 500000000000,00"),
                "Выводится S огромного треугольника");
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}

