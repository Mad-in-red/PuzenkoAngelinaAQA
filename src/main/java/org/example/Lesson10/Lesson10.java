package org.example.Lesson10;

public class Lesson10 {

    public static void main(String[] args) {

        Product product1 = new Product("телефон", "12.05.2024",
                "sony", "China", 18571.60, true);

        product1.info();

        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("мясорубка", "15.01.2022",
                "Bosch", "Taiwan", 5761.4, false);
        productsArray[1] = new Product("фен", "19.04.2023",
                "Vitek", "China", 3700, false);
        productsArray[2] = new Product("миксер", "18.04.2024",
                "Bosch", "Taiwan", 7351.40, true);
        productsArray[3] = new Product("телефон", "13.02.2025",
                "sony", "Taiwan", 25869.90, true);
        productsArray[4] = new Product("телефон", "18.12.2023",
                "Apple", "Taiwan", 124958, false);

        Park.AttractionInfo  attract1 = new Park("Американские горки") .new AttractionInfo("с 10 утра до 21 вечера", 200);
        attract1.attractionInformation();

    }
}
