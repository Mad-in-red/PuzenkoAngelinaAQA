package org.example.Lesson11.GeometricShapes;

public class Rectangles implements Metods{

    protected String colorF;
    protected String colorB;
    protected double dlina;
    protected double shirina;

    public Rectangles (String colorF, String colorB, double dlina, double shirina){
        this.colorF = colorF;
        this.colorB = colorB;
        this.dlina = dlina;
        this.shirina = shirina;
    }

    @Override
    public double calcP() {
        return  (dlina + shirina) * 2;
    }

    @Override
    public double calcS() {
        return dlina * shirina;
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
