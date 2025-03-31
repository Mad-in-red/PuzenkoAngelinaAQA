package org.example.Lesson10;

public class Product {

    private String title;
    private String productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean  statusBookingByBuyer;

    public Product ( String title, String productionDate, String manufacturer,
                     String countryOfOrigin, double price, boolean  statusBookingByBuyer){
        this.title = title;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.statusBookingByBuyer = statusBookingByBuyer;

    }

    public void info(){
        System.out.printf(" title: %s\n productionDate: %s\n" +
                " manufacturer: %s\n countryOfOrigin: %s\n price: %.2f\n" +
                " statusBookingByBuyer: %b\n",title, productionDate, manufacturer,
                countryOfOrigin, price, statusBookingByBuyer);
    }



}
