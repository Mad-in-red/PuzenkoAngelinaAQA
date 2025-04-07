package org.example.Lesson12;

public class Lesson12 {
    public static void main(String[] args) {
        ArraysMethods array1 = new ArraysMethods();
        String[][] arrayOne = {
                {"2", "1", "3", "4", "89"},
                {"5", "6", "7", "8"},
                {"9", "text", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            array1.creatingArray(arrayOne);
        } catch (MyArraySizeException e) {
            System.err.println(e.getMessage());

        }

        ArraysMethods array2 = new ArraysMethods();
        String[][] arrayTwo = {
                {"2", "1", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "text", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int sum = array2.sumArray(arrayTwo);
            System.out.println("Сумма элементов массива: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        ArraysMethods array3 = new ArraysMethods();
        String[][] arrayTree = {
                {"2", "1", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "152", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            System.out.println(arrayOne[4][0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            int sum = array3.sumArray(arrayTree);
            System.out.println("Сумма элементов массива: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }






}






