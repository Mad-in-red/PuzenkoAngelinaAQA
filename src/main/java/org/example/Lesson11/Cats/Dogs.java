package org.example.Lesson11.Cats;

public class Dogs extends Animals {
    static int allDogs = 0;

    private String name;
    private int age;
    private String color;
    private String breed;

    public Dogs(String name, int age, String color, String breed){
        super();
        allDogs++;
        this.name = name;
        this.age = age;
        this.color = color;
        this.breed = breed;
    }

    @Override
    public void run(int l){
        if (l <= 0){
            System.out.printf("Бегать отрицательное или 0 расстояние нельзя, собака %s осталась на месте\n", name);
        } else if (l <= 500){
            System.out.printf("Собака %s пробежала %d м.\n", name, l);
        } else {
            System.out.printf("Собака %s пробежала 500м и устала. Теперь она отдыхает\n", name);
        }

    }

    @Override
    public void swim(int l){
        if (l <= 0){
            System.out.printf("Собака %s постояла в воде\n", name);
        } else if (l <= 10){
            System.out.printf("Собака %s проплыла %d м.\n", name, l);
        } else {
            System.out.printf("Собака %s проплыла 10м и вылезла из воды\n", name);
        }
    }

    public static void countingDogs(){
        System.out.println("Всего собак: " + allDogs);;
    }

}
