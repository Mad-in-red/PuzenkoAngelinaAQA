package org.example.Lesson11.Cats;

public class Cats extends Animals {
    static int allCats = 0;
    int oneCatEats = 5;



    private String name;
    private int age;
    private String color;
    private String typeOfFur;
    private boolean fullness;

    public Cats(String name, int age, String color, String typeOfFur, boolean fullness){
       super();
       allCats++;
       this.name = name;
       this.age = age;
       this.color = color;
       this.typeOfFur = typeOfFur;
       this.fullness = fullness;
    }

    @Override
    public void run(int l){
        if (l <= 0){
            System.out.printf("Кот %s смотрит на вас с презрением, даже он знает что пробежать отрицательное расстояние не получится\n", name);
        } else if (l <= 200){
            System.out.printf("Кот %s пробежал %d м.\n", name, l);
        } else {
            System.out.printf("Кот %s пробежал 200м и устал. Теперь он делает любимое упражнение: леж лежа\n", name);
        }
    }

    @Override
    public void swim(int l){
        System.out.printf("Кот %s никуда не поплыл, он не умеет плавать, но скрывает это за высокомерным взглядом\n", name);
    }

    public static void countingCats(){
        System.out.println("Всего котов: " + allCats);;
    }

    public void feedingCats(Bowl bowl){

        if (fullness) {
            System.out.printf("Кот %s сыт и игнорирует миску с едой\n", name);
            return;
        }

        fullness = bowl.getFeed(oneCatEats);

            if (fullness) {
                System.out.printf("Кот %s накормлен и доволен\n", name);
            } else {
                System.out.printf("Котику %s нехватило еды, насыпьте корм в миску\n", name);
            }
    }
}
