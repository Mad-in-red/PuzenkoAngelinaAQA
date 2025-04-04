package org.example.Lesson11.GeometricShapes;

public class Triangles implements Metods{

    protected String colorF;
    protected String colorB;
    protected double side1;
    protected double side2;
    protected double side3;

    public Triangles (String colorF, String colorB,double side1, double side2, double side3){
        this.colorF = colorF;
        this.colorB = colorB;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double calcP() {
        return side1 + side2 + side3;
    }

    @Override
    public double calcS() {
        double p = (side1 + side2 + side3) / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    @Override
    public String getColorF() {
        return colorF;
    }

    @Override
    public String getColorB() {
        return colorB;
    }

}
