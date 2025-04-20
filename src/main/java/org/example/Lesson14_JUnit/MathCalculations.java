package org.example.Lesson14_JUnit;

public class MathCalculations {

    public static double doOperation(double a, double b, String toDo) {
        double c = 0;
        switch (toDo) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("На 0 делить нельзя!");
                return a / b;
            default:
                throw new IllegalArgumentException("Неизвестная операция: " + toDo);
        }

    }

    public static String comparingNumbers(int a, int b) {
        if (a == b) {
            return "Числа равны";
        } else {
            return a > b ? a + " > " + b : a + " < " + b;
        }
    }

    public static double calcArea(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Длины сторон должны быть положительными!");
        }
        if (!(a + b > c && a + c > b && b + c > a)) {
            throw new IllegalArgumentException("Треугольник с такими сторонами не существует!");
        }
        return calcS(a, b, c);
    }

    public static double calcS(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал определен только для n >= 0");
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = Math.multiplyExact(result, i);
        }
        return result;
    }

}