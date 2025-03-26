public class Lesson9 {

    public static void main(String[] args) {

        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        taskFive(5, 3);
        positiveChislo(0);
        wholeChislo(-8);
        taskEight("Задание 8 выполнено", 2);
        leapYear(1971);
        taskTen(1, 1, 0, 0, 1, 0, 1, 1, 0, 0);
        taskEleven();
        taskTwelve(1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1);
        diagonals();
        twoArgs(4, 215);
    }

    //task1
    static void printThreeWords() {
        System.out.println("Orange \nBanana \nApple");
    }

    //task2
    static void checkSumSign() {
        int a = 9;
        int b = 23;
        String rez = (a + b) >= 0 ? "Сумма положительная" : "Сумма отрицательная";
        System.out.println(rez);
    }

    //task3
    static void printColor() {
        int value = 150;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else if (value > 100) {
            System.out.println("Зеленый");
        }
    }

    //task4
    static void compareNumbers() {
        int a = 7;
        int b = 3;
        String rez = a >= b ? "a >= b" : "a < b";
        System.out.println(rez);
    }

    //task5
    static void taskFive(int a, int b) {
        boolean rez = (a + b) >= 10 && (a + b) <= 20;
        System.out.println(rez);
    }

    //task6
    static void positiveChislo(int a) {
        String rez = a >= 0 ? "Положительное" : "Отрицательное";
        System.out.println(rez);
    }

    //task7
    static void wholeChislo(int a) {
        boolean rez = a <= 0;
        System.out.println(rez);
    }

    //task8
    static void taskEight(String s, int b) {
        for (int i = 1; i <= b; i++) {
            System.out.println(s);
        }
    }

    //task9
    static void leapYear(int a) {
        boolean b = false;
        if (a % 400 == 0) {
            b = true;
        } else if (a % 100 == 0) {
            b = false;
        } else if (a % 4 == 0) {
            b = true;
        }
        System.out.println(b);
    }

    //task10
    static void taskTen(int... arr) {
        for (int i = 0; i <= arr.length - 1; i++) {

            switch (arr[i]){
                case 0:
                    arr[i] = 1;
                    System.out.print(arr[i]);
                    break;
                case 1:
                    arr[i] = 0;
                    System.out.print(arr[i]);
                    break;
            }
        }
        System.out.println();
    }

    //task11
    static void taskEleven(){
        int[] array = new int[100];

        for (int i = 0; i <= array.length - 1; i++){
            array[i] = i + 1;
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    //task12
    static void taskTwelve(int ...arr){

        for (int i = 0; i <= arr.length - 1; i++){
            if (arr[i] < 6){
                arr[i] *= 2;
                System.out.print(arr[i] + " ");
            }
            else{
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

    //task13
    static void diagonals(){

        int[][] array = new int[11][11];

        for (int i = 0; i <= array.length - 1; i++){
            for (int j = 0; j <= array.length - 1; j++){
                if (i == j) {
                    array[i][j] = 1;
                }
                else if (i + j == array.length - 1) {
                    array[i][j] = 1;
                }
                else {
                    array[i][j] = 0;
                }

                System.out.print(array[i][j]);

            }
            System.out.println();
        }
    }

    //task14
    static void twoArgs(int len, int initialValue){
        int[] array = new int[len];
        for (int i = 0; i <= array.length - 1; i++){
            array[i] = initialValue;
            System.out.print(array[i] + " ");
        }
    }

}
