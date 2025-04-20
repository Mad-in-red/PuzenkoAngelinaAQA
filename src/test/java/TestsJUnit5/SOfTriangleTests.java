package TestsJUnit5;

import org.example.Lesson14_JUnit.MathCalculations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SOfTriangleTests {
    @Test
    void testValidTriangleArea() {
        double area = MathCalculations.calcArea(5.5, 5.5, 6);
        assertEquals(13.83, area, 0.01, "Площадь треугольника вычислена неверно");
    }

    @Test
    void testBigNumbers() {
        double area = MathCalculations.calcArea(1000000, 1000000, 1414213.56);
        assertEquals(500000000000.0, area, 1.0, "Площадь для больших чисел вычислена неверно");
    }

    @Test
    void testNegativeSides() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> MathCalculations.calcArea(-5, 5, 5),
                "Ожидалось исключение для отрицательных сторон"
        );
        assertEquals("Длины сторон должны быть положительными!", exception.getMessage());
    }

    @Test
    void testInvalidTriangle() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> MathCalculations.calcArea(1, 2, 10),
                "Ожидалось исключение для несуществующего треугольника"
        );
        assertEquals("Треугольник с такими сторонами не существует!", exception.getMessage());
    }

}
