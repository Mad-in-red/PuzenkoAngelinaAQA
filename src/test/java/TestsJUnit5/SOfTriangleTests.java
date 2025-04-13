package TestsJUnit5;

import org.example.Lesson14JUnut5.SOfTriangle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SOfTriangleTests {
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
        String input = "abc\n-10\n0\n5,5\n27\n5,5\n";
        provideInput(input);
        SOfTriangle.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Ошибка: введите положительное число!"));
        assertTrue(output.contains("Треугольник с такими сторонами не существует!"));
    }
    //Технически, можно добавить проверку на отрицательно число и 0 отдельно,
    // но мне показалось что это избыточные проверки тк в testInvalidNumbersInput мы проверяем
    //эти значения. Так же тут треугольник проверяется на существовние, что тоже можно сделать отдельным тестом.
    // Прошу в комментариях написать верно ли это решение или все же надо добавить эти проверки?

    @Test
    void testCalcArea() {
        String input = "5,5\n5,5\n6";
        provideInput(input);
        SOfTriangle.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Площадь треугольника с этими сторонами равна 13,83"));
    }

    @Test
    void testBigNumbers() {
        String input = "1000000\n1000000\n1414213,56\n";
        provideInput(input);
        SOfTriangle.main(new String[]{});
        String output = outContent.toString().replace("\r", "");
        assertTrue(output.contains("Площадь треугольника с этими сторонами равна 500000000000,00"));
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

}
