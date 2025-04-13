package org.example.Lesson12;

public class Lesson12 {
    public static void main(String[] args) {
        ArraysMethods arraysMethods = new ArraysMethods();

        String[][] array1 = {
                {"2", "1", "3", "4", "89"},
                {"5", "6", "7", "8"},
                {"9", "23", "11", "12", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            System.out.println("Сумма: " + arraysMethods.checkSizeAndSummArray(array1));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println(e.getMessage());

        }

        String[][] array2 = {
                {"2", "1", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "text", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            System.out.println("Сумма: " + arraysMethods.checkSizeAndSummArray(array2));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        String[][] array3 = {
                {"2", "1", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "152", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            System.out.println("Сумма: " + arraysMethods.checkSizeAndSummArray(array3));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            String[][] array4 = new String[4][4];
            System.out.println(array4[4][0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }


}






