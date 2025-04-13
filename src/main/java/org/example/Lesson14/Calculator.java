package org.example.Lesson14;

import java.util.Scanner;

public class Calculator implements ReadingScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        double a = calculator.readDoubleNumber(scanner, "Введите первое число: ");
        double b = calculator.readDoubleNumber(scanner, "Введите второе число: ");
        scanner.nextLine();
        String toDo = calculator.readOperation(scanner, "Введите операцию (+, -, *, /): ");
        scanner.close();

        calculator.doOperation(a, b, toDo);

    }

    public void doOperation(double a, double b, String toDo) {
        switch (toDo) {
            case "+":
                System.out.printf("%.2f + %.2f = %.2f", a, b, a + b);
                break;
            case "-":
                System.out.printf("%.2f - %.2f = %.2f", a, b, a - b);
                break;
            case "*":
                System.out.printf("%.2f * %.2f = %.2f", a, b, a * b);
                break;
            case "/":
                if (b == 0) {
                    System.out.println("На 0 делить нельзя!");
                } else {
                    System.out.printf("%.2f / %.2f = %.2f", a, b, a / b);
                    break;
                }
        }
    }
}
