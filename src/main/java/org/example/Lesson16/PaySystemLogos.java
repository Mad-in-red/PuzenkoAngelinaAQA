package org.example.Lesson16;

import java.util.Arrays;
import java.util.List;

public enum PaySystemLogos {
    VISA("Visa", "//*[@id='pay-section']//ul/li[1]/img"),
    VERIFIED_BY_VISA("Verified by Visa", "//*[@id='pay-section']//ul/li[2]/img"),
    MASTERCARD("MasterCard", "//*[@id='pay-section']//ul/li[3]/img"),
    MASTERCARD_SECURE_CODE("MasterCard SecureCode", "//*[@id='pay-section']//ul/li[4]/img"),
    BELKART("Белкарт", "//*[@id='pay-section']//ul/li[5]/img");


    public String name;
    public String xpath;

    PaySystemLogos(String name, String xpath) {
        this.name = name;
        this.xpath = xpath;
    }

}
