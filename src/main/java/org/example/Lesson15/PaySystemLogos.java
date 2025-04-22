package org.example.Lesson15;

import java.util.Arrays;
import java.util.List;

public class PaySystemLogos {
    public String name;
    public String xpath;

    public PaySystemLogos(String name, String xpath) {
        this.name = name;
        this.xpath = xpath;
    }

    public static List<PaySystemLogos> getPaymentSystemLogosToVerify() {
        return Arrays.asList(
                new PaySystemLogos("Visa", "//*[@id='pay-section']//ul/li[1]/img"),
                new PaySystemLogos("Verified by Visa", "//*[@id='pay-section']//ul/li[2]/img"),
                new PaySystemLogos("MasterCard", "//*[@id='pay-section']//ul/li[3]/img"),
                new PaySystemLogos("MasterCard SecureCode", "//*[@id='pay-section']//ul/li[4]/img"),
                new PaySystemLogos("Белкарт", "//*[@id='pay-section']//ul/li[5]/img")
        );

    }
}
