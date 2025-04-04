package org.example.Lesson11.Cats;

public class DZ1 {
    public static void main(String[] args) {

        Cats[] cats = new Cats[5];
        cats[0] = new Cats ("Кусака", 2, "черный", "короткошерстный", true);
        cats[1] = new Cats ("Пирожок", 3, "рыжий", "короткошерстный", false);
        cats[2] = new Cats ("Мурчелло", 1, "белый", "длинношерстный", false);
        cats[3] = new Cats ("Пушинка", 8, "шоколадный", "длинношерстный", false);
        cats[4] = new Cats ("Семён", 5, "серый", "короткошерстный", false);

        Dogs[] dogs = new Dogs[3];
        dogs[0] = new Dogs ("Мухтар", 1, "черно-коричневый", "овчарка");
        dogs[1] = new Dogs ("Жучка", 3, "белый с коричневыми пятнами", "дворняжка");
        dogs[2] = new Dogs ("Арчи", 5, "черный", "лабрадор");

        cats[3].swim(5);
        cats[1].run(205);
        cats[4].run(-7);
        cats[0].run(50);
        dogs[0].run(250);
        dogs[1].swim(12);
        dogs[2].run(0);

        Cats.countingCats();
        Dogs.countingDogs();
        System.out.println("Всего животных: " + Animals.allAnimal);

        Bowl bowl = new Bowl(10);
        bowl.fillBowl(12);

        for (Cats cat : cats) {
            cat.feedingCats(bowl);
        }
    }
}
