package org.example.Lesson11.GeometricShapes;

public interface Metods {

    double calcP();
    double calcS();
    String getColorF();
    String getColorB();
    default void getInfo(){
        System.out.printf(" Triangl's P: %.2f\n Triangl's S: %.2f\n" +
                " Color of triangle: %s\n color of triangl's borders: %s\n",
                calcP(), calcS(), getColorF(), getColorB());
    }


}
