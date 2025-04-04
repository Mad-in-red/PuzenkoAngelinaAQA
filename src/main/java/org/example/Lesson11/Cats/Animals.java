package org.example.Lesson11.Cats;

public class Animals {
    static int allAnimal = 0;

    public Animals(){
        counting();
    }

    void counting(){
        allAnimal++;
    }

    public void run(int l){
        System.out.printf("Животное пробежало %d м.", l);
    }

    public void swim(int l){
        System.out.printf("Животное проплыло %d м.", l);
    }
}
