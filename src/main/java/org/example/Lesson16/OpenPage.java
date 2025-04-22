package org.example.Lesson16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public OpenPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    protected void closeCookieBanner() {
        try {
            WebElement cookieBanner = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'cookie')]")));
            WebElement acceptButton = cookieBanner.findElement(
                    By.xpath(".//button[contains(., 'Принять') or contains(., 'Согласен')]"));
            acceptButton.click();
            wait.until(ExpectedConditions.invisibilityOf(cookieBanner));
        } catch (Exception e) {
            System.out.println("Cookie banner не найден или не может быть закрыт");
        }
    }
}

