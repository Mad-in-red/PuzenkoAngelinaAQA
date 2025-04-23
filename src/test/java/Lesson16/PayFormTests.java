package Lesson16;

import org.example.Lesson16.PaymentFrame;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PayFormTests extends BaseTest {
    @Test
    public void testCommunicationServicesPayment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PaymentFrame paymentFrame = new PaymentFrame(driver);

        serviceForm.selectService("Услуги связи");

        homePageMts.fillPhoneNumber("297777777");
        homePageMts.fillAmount("77");

        homePageMts.clickContinueButton();

        Assert.assertTrue(homePageMts.waitForPaymentFrame(),
                "Платежный фрейм не загрузился");

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.cssSelector("iframe.bepaid-iframe")));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[formcontrolname=creditCard] + label")));

            String displayedPhone = paymentFrame.getPhoneNumber();
            Assert.assertTrue(displayedPhone.contains("375297777777"),
                    "Номер телефона отображается некорректно: " + displayedPhone);

            String displayedAmount = wait.until(driver -> driver.findElement(
                    By.cssSelector("div.pay-description__cost span:nth-child(1)")).getText());
            Assert.assertEquals(displayedAmount, "77.00 BYN",
                    "Сумма платежа отображается некорректно: " + displayedAmount);

            checkPlaceholderAndAssert(paymentFrame,
                    "input[formcontrolname=creditCard] + label",
                    "Номер карты",
                    "поля номера карты");

            checkPlaceholderAndAssert(paymentFrame,
                    "input[formcontrolname=expirationDate] + label",
                    "Срок действия",
                    "поля срока карты");

            checkPlaceholderAndAssert(paymentFrame,
                    "input[formcontrolname=cvc] + label",
                    "CVC",
                    "поля CVC");

            checkPlaceholderAndAssert(paymentFrame,
                    "input[formcontrolname=holder] + label",
                    "Имя держателя (как на карте)",
                    "поля имени держателя");

            Assert.assertTrue(
                    isImageDisplayed(wait, "mastercard-system.svg"),
                    "Иконка Mastercard не отображается");

            Assert.assertTrue(
                    isImageDisplayed(wait, "visa-system.svg"),
                    "Иконка Visa не отображается");

            Assert.assertTrue(
                    isImageDisplayed(wait, "belkart-system.svg"),
                    "Иконка Белкарт не отображается");

            String payButtonText = wait.until(driver -> driver.findElement(
                    By.cssSelector("div.card-page__card button")).getText());
            Assert.assertEquals(payButtonText, "Оплатить 77.00 BYN",
                    "Текст на кнопке оплаты не совпадает: " + payButtonText);

        } finally {
            driver.switchTo().defaultContent();
        }
    }

    private boolean isImageDisplayed(WebDriverWait wait, String src) {
        return wait.until(driver -> {
            return driver.findElements(By.cssSelector("div.cards-brands img")).stream()
                    .anyMatch(img -> img.getAttribute("src").contains(src) &&
                            (img.isDisplayed() || img.getAttribute("style").contains("opacity: 0")));
        });
    }

    private void checkPlaceholderAndAssert(PaymentFrame paymentFrame,
                                           String locator,
                                           String expectedText,
                                           String fieldName) {
        PaymentFrame.PlaceholderCheckResult result = paymentFrame.checkPlaceholder(locator, expectedText, fieldName);
        Assert.assertTrue(result.isSuccess(), result.getMessage());
    }
}
