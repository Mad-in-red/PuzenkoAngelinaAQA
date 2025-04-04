package org.example.Lesson11.GeometricShapes;

public class Circles implements Metods{

    protected String colorF;
    protected String colorB;
    protected double radius;

    public Circles (String colorF, String colorB,double radius){
        this.colorF = colorF;
        this.colorB = colorB;
        this.radius = radius;
    }

    @Override
    public double calcP() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double calcS() {
        return Math.PI * Math.pow(radius, 2);
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
