package org.example.Lesson13;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lesson13 {

    public static void main(String[] args) {

        students.get(0).delStudent((ArrayList<Student>) students);
        students.get(1).transferStudentNextCourse();
        Set<Student> studentSet = new HashSet<>(students);
        Student.printStudents(studentSet, 3);

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addNumber("Иванов", "8-918-312-02-10");
        phoneBook.addNumber("Иванов", "8-951-326-85-41");
        phoneBook.addNumber("Васильев", "8-961-235-51-24");
        phoneBook.addNumber("Пупкин", "8-918-312-22-12");

        System.out.println("Иванов: " + phoneBook.getNumber("Иванов"));
        System.out.println("Пупкин: " + phoneBook.getNumber("Пупкин"));
    }

    public static List<Student> students = new ArrayList<Student>() {
        {
            add(new Student("Иван", "Иванов", 28, 1, 1, 1, 1, 1));
            add(new Student("Василий", "Пупкин", 28, 1, 5, 5, 5, 5));
            add(new Student("Александр", "Петров", 26, 2, 3, 4, 3, 3));
            add(new Student("Мария", "Рыжова", 26, 2, 5, 4, 5, 4));
            add(new Student("Валентина", "Пряхина", 32, 3, 3, 2, 1, 3));
            add(new Student("Дарья", "Донцова", 32, 3, 2, 5, 3, 3));
            add(new Student("Федор", "Двинятин", 23, 4, 5, 4, 3, 5));
            add(new Student("Дарья", "Фролова", 24, 4, 5, 4, 4, 5));
            add(new Student("Руслан", "Романов", 15, 5, 5, 5, 5, 5));
            add(new Student("Иван", "Иванов", 16, 5, 5, 4, 3, 5));
        }
    };
}

