package TestsTestng;

import org.example.Lesson14_Testng.MathCalculations;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SOfTriangleTests {

    @Test
    public void testValidTriangleArea() {
        double area = MathCalculations.calcArea(5.5, 5.5, 6);
        assertEquals(area, 13.83, 0.01, "Площадь треугольника вычислена неверно");
    }

    @Test
    public void testBigNumbers() {
        double area = MathCalculations.calcArea(1000000, 1000000, 1414213.56);
        assertEquals(area, 500000000000.0, 1.0, "Площадь для больших чисел вычислена неверно");
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Длины сторон должны быть положительными!")
    public void testNegativeSides() {
        MathCalculations.calcArea(-5, 5, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Треугольник с такими сторонами не существует!")
    public void testInvalidTriangle() {
        MathCalculations.calcArea(1, 2, 10);
    }

}

