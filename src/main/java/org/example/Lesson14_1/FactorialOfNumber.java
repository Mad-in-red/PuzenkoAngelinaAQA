package org.example.Lesson14_1;

import org.example.Lesson14.ReadingScanner;

import java.util.Scanner;

public class FactorialOfNumber implements ReadingScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FactorialOfNumber factorialOfNumber = new FactorialOfNumber();
        long a = factorialOfNumber.readLongNumber(scanner, "Введите положительное число: ");

        factorialOfNumber.calcFactorial(a);

    }

    public void calcFactorial(long a){
        long factorial = 1;
        try {
            for (int i = 1; i <= a; i++) {
                factorial = Math.multiplyExact(factorial, i);
            }
            System.out.println("Факториал " + a + " = " + factorial);
        } catch (ArithmeticException e) {
            System.out.println("Произошло переполнение при вычислении факториала!");
        }
    }
}
