package org.example.Lesson10;

public class Park {

    protected String nameOfAttraction;
    protected AttractionInfo attractionInfo;

    public Park (String nameOfAttraction) {
        this.nameOfAttraction = nameOfAttraction;

    }

    public class AttractionInfo{

        protected String workingTime;
        protected double price;

        public AttractionInfo (String workingTime, double price) {
            this.workingTime = workingTime;
            this.price = price;
        }

        public void attractionInformation(){
            System.out.printf(" название: %s\n время работы: %s\n цена: %.2f $\n", nameOfAttraction,workingTime, price);

        }
    }



}
