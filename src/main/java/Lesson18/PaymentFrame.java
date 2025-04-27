package Lesson18;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentFrame extends OpenPage {
    private final WebDriverWait wait;

    private final By iframeLocator = By.cssSelector("iframe.bepaid-iframe");
    private final By phoneNumberLocator = By.cssSelector("div.pay-description__text span:nth-child(1)");
    private final By amountLocator = By.cssSelector("div.pay-description__cost span:nth-child(1)");
    private final By cardFormLocator = By.cssSelector("div.card-page__card");
    private final By cardNumberPlaceholderLocator = By.cssSelector("input[formcontrolname=creditCard] + label");
    private final By expiryDatePlaceholderLocator = By.cssSelector("input[formcontrolname=expirationDate] + label");
    private final By cvcPlaceholderLocator = By.cssSelector("input[formcontrolname=cvc] + label");
    private final By cardHolderPlaceholderLocator = By.cssSelector("input[formcontrolname=holder] + label");

    public PaymentFrame(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void switchToPaymentFrame() {
        WebElement frameElement = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
        driver.switchTo().frame(frameElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardFormLocator));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getPhoneNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberLocator)).getText();
    }

    public String getPaymentAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(amountLocator)).getText();
    }

    public String getFieldPlaceholder(String fieldName) {
        switch (fieldName) {
            case "Поле ввода номера карты":
                return wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberPlaceholderLocator)).getText();
            case "Поле ввода срока действия":
                return wait.until(ExpectedConditions.visibilityOfElementLocated(expiryDatePlaceholderLocator)).getText();
            case "Поле ввода CVC":
                return wait.until(ExpectedConditions.visibilityOfElementLocated(cvcPlaceholderLocator)).getText();
            case "Поле имени держателя":
                return wait.until(ExpectedConditions.visibilityOfElementLocated(cardHolderPlaceholderLocator)).getText();
            default:
                throw new IllegalArgumentException("Неизвестное имя поля: " + fieldName);
        }
    }
}