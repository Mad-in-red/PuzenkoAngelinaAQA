package org.example.Lesson14_JUnit;

public class FactorialOfNumber {

    public static void main(String[] args) {

        int number = 5;
        long factorialResult = MathCalculations.factorial(number);
        System.out.printf("Факториал %d = %d", number, factorialResult);

    }

}
