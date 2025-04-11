package org.example.Lesson13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Student {
    private String name;
    private String surname;
    private int group;
    private int course;
    private int mathScore;
    private int biologyScore;
    private int chemistryScore;
    private int physicsScore;

    public Student(String name, String surname, int group, int course,
                   int mathScore, int biologyScore, int chemistryScore, int physicsScore) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.course = course;
        this.mathScore = mathScore;
        this.biologyScore = biologyScore;
        this.chemistryScore = chemistryScore;
        this.physicsScore = physicsScore;
    }

    public double getAverageScore() {
        return (double) (mathScore + biologyScore + chemistryScore + physicsScore) / 4;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getCourse() {
        return course;
    }


    public void delStudent(ArrayList<Student> students) {
        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageScore() < 3) {
                iterator.remove();
            }
        }
    }

    public void transferStudentNextCourse() {
        if (course < 5 && getAverageScore() >= 3) {
            this.course++;
        } else {
            System.out.println("Поздравляем с завершением обучения в нашей шараге!");
        }
    }

    public static void printStudents(Set<Student> students, int course) {

        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName() + " " + student.getSurname());
            }
        }

    }

}
