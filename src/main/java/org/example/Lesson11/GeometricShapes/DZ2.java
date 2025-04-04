package org.example.Lesson11.GeometricShapes;

public class DZ2 {

    public static void main(String[] args) {

        Rectangles rec1 = new Rectangles("red", "black",5,20);
        rec1.getInfo();

        Triangles tri1 = new Triangles("yellow", "black",5,8,6);
        tri1.getInfo();

        Circles cir1 = new Circles("green", "black",12.5);
        cir1.getInfo();

    }
}
