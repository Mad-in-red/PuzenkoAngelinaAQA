package org.example.Lesson14;

import java.util.Scanner;

public class ComparingNumbers implements ReadingScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ComparingNumbers comparator = new ComparingNumbers();
        int a = comparator.readIntNumber(scanner, "Введите первое число: ");
        int b = comparator.readIntNumber(scanner, "Введите второе число: ");
        scanner.close();

        comparator.Comparing(a, b);

    }

    public void Comparing(int a, int b) {
        if (a == b) {
            System.out.println("Числа равны");
        } else if (a > b) {
            System.out.printf("%d > %d", a, b);
        } else {
            System.out.printf("%d < %d", a, b);
        }
    }

}
