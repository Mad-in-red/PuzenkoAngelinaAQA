package org.example.Lesson16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Supplier;

public class PaymentFrame extends OpenPage {

    private final WebDriverWait wait;

    public PaymentFrame(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public String getPhoneNumber() {
        return getElementText(() ->
                driver.findElement(By.cssSelector("div.pay-description__text span:nth-child(1)")));
    }

    private String getElementText(Supplier<WebElement> elementSupplier) {
        try {
            return wait.until(driver -> elementSupplier.get().getText());
        } catch (Exception e) {
            return "";
        }
    }

    public PlaceholderCheckResult checkPlaceholder(String locator, String expectedText, String fieldName) {
        try {
            String actualText = getElementText(() ->
                    driver.findElement(By.cssSelector(locator)));
            boolean matches = actualText.equals(expectedText);
            String message = matches ? "" :
                    String.format("Неверная надпись в %s. Ожидалось: '%s', Фактически: '%s'",
                            fieldName, expectedText, actualText);
            return new PlaceholderCheckResult(matches, message);
        } catch (Exception e) {
            return new PlaceholderCheckResult(false,
                    String.format("Ошибка при проверке поля %s: %s", fieldName, e.getMessage()));
        }
    }

    public static class PlaceholderCheckResult {
        private final boolean success;
        private final String message;

        public PlaceholderCheckResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
