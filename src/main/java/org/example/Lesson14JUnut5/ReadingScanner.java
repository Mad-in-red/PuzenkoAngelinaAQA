package org.example.Lesson14JUnut5;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface ReadingScanner {
    default int readIntNumber(Scanner scanner, String promptMessage) {
        while (true) {
            try {
                System.out.print(promptMessage);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число!");
                scanner.next();
            }
        }
    }

    default double readDoubleNumber(Scanner scanner, String promptMessage) {
        while (true) {
            try {
                System.out.print(promptMessage);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число!");
                scanner.next();
            }
        }
    }

    default String readOperation(Scanner scanner, String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String input = scanner.nextLine().trim();
            if (input.matches("[+\\-*/]")) {
                return input;
            }
            System.out.println("Ошибка: допустимые операции +, -, *, /");
        }
    }

    default double readDoubleForTriangle(Scanner scanner, String promptMessage) {
        while (true) {
            try {
                System.out.print(promptMessage);
                double number = scanner.nextDouble();
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Ошибка: введите положительное число!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите положительное число!");
                scanner.next();
            }
        }
    }

    default Long readLongNumber(Scanner scanner, String promptMessage) {
        while (true) {
            try {
                System.out.print(promptMessage);
                long number = scanner.nextLong();
                if (number > 0 && number <= 20 ) {
                    return number;
                } else {
                    System.out.println("Ошибка: введите положительное число <= 20!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите положительное число!");
                scanner.next();
            }
        }
    }

}



