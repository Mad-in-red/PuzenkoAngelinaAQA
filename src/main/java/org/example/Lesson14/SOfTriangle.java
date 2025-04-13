package org.example.Lesson14;

import java.util.Scanner;

public class SOfTriangle implements ReadingScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SOfTriangle areaOfTriangle = new SOfTriangle();
        double a = areaOfTriangle.readDoubleForTriangle(scanner, "Введите длину первой стороны треугольника: ");
        double b = areaOfTriangle.readDoubleForTriangle(scanner, "Введите длину второй стороны треугольника: ");
        double c = areaOfTriangle.readDoubleForTriangle(scanner, "Введите длину третьей стороны треугольника: ");
        scanner.close();

        calcArea(a, b, c);

    }

    public static double calcArea(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            System.out.println("Длины сторон должны быть положительными!");
            return -1;
        }
        if (a + b > c && a + c > b && b + c > a) {
            double area = calcS(a, b, c);
            System.out.printf("Площадь треугольника с этими сторонами равна %.2f", area);
            return area;
        } else {
            System.out.println("Треугольник с такими сторонами не существует!");
            return -1;
        }
    }

    public static double calcS(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}