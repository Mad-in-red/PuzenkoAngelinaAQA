package org.example.Lesson15;

import java.util.Arrays;
import java.util.List;

public class PaySystemLogo {
    public String name;
    public String xpath;

    public PaySystemLogo(String name, String xpath) {
        this.name = name;
        this.xpath = xpath;
    }

    public static List<PaySystemLogo> getPaymentSystemLogosToVerify() {
        return Arrays.asList(
                new PaySystemLogo("Visa", "//*[@id='pay-section']//ul/li[1]/img"),
                new PaySystemLogo("Verified by Visa", "//*[@id='pay-section']//ul/li[2]/img"),
                new PaySystemLogo("MasterCard", "//*[@id='pay-section']//ul/li[3]/img"),
                new PaySystemLogo("MasterCard SecureCode", "//*[@id='pay-section']//ul/li[4]/img"),
                new PaySystemLogo("Белкарт", "//*[@id='pay-section']//ul/li[5]/img")
        );

    }
}
