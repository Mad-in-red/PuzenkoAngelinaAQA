package Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContinueButtonTest {
    @Test
    public void testContinueButtonFunctionality() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("https://www.mts.by");

            closeCookieBanner(driver, wait);

            fillPhoneNumber(driver, wait, "297777777");

            fillAmount(driver, wait, "500");

            clickContinueButton(driver, wait);

            checkPaymentFormOpened(driver, wait);

            System.out.println("Тест успешно пройден: платежная форма отобразилась");

        } catch (Exception e) {
            System.err.println("Ошибка при выполнении теста: " + e.getMessage());
            takeScreenshot(driver);
            Assert.fail("Тест завершился с ошибкой: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    private void closeCookieBanner(WebDriver driver, WebDriverWait wait) {
        try {
            WebElement cookieBanner = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'cookie')]")));
            WebElement acceptButton = cookieBanner.findElement(
                    By.xpath(".//button[contains(., 'Принять') or contains(., 'Согласен')]"));
            acceptButton.click();
            wait.until(ExpectedConditions.invisibilityOf(cookieBanner));
            System.out.println("Cookie banner успешно закрыт");
        } catch (Exception e) {
            System.out.println("Cookie banner не найден или не может быть закрыт - продолжаем тест");
        }
    }

    private void fillPhoneNumber(WebDriver driver, WebDriverWait wait, String phoneNumber) {
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-phone")));
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
        System.out.println("Заполнен номер телефона: " + phoneNumber);
    }

    private void fillAmount(WebDriver driver, WebDriverWait wait, String amount) {
        WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-sum")));
        sumField.clear();
        sumField.sendKeys(amount);
        System.out.println("Заполнена сумма: " + amount);
    }

    private void clickContinueButton(WebDriver driver, WebDriverWait wait) {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='pay-connection']/button")));
        continueButton.click();
        System.out.println("Нажата кнопка 'Продолжить'");
    }

    private void checkPaymentFormOpened(WebDriver driver, WebDriverWait wait) {
        try {

            WebElement paymentFrame = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("iframe.bepaid-iframe")));

            boolean isFrameEnabled = paymentFrame.isEnabled();

            System.out.println("Платежная форма обнаружена:");
            System.out.println("- iframe доступен: " + isFrameEnabled);


        } catch (TimeoutException e) {
            System.out.println("Текущий HTML страницы:");
            System.out.println(driver.getPageSource());
            takeScreenshot(driver);
            throw new AssertionError("Платежная форма не отобразилась: не найден iframe.bepaid-iframe");
        }
    }

    private void takeScreenshot(WebDriver driver) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Здесь можно сохранить скриншот в файл если нужно
            System.out.println("Сделан скриншот для анализа");
        } catch (Exception e) {
            System.err.println("Не удалось сделать скриншот: " + e.getMessage());
        }
    }
}