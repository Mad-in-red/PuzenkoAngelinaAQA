package Lesson15TestsBlockOnlineReplenishmentWithoutCommission;

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
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("https://www.mts.by");

            try {
                WebElement cookieBanner = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'cookie')]")));
                WebElement acceptButton = cookieBanner.findElement(
                        By.xpath(".//button[contains(., 'Принять') or contains(., 'Согласен')]"));
                acceptButton.click();
                wait.until(ExpectedConditions.invisibilityOf(cookieBanner));
            } catch (Exception e) {
                System.out.println("Cookie banner не найден или не может быть закрыт - продолжаем тест");
            }

            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("connection-phone")));
            phoneField.clear();
            phoneField.sendKeys("297777777");

            WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("connection-sum")));
            sumField.clear();
            sumField.sendKeys("500");

            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='pay-connection']/button")));
            continueButton.click();

            try {
                wait.until(ExpectedConditions.invisibilityOf(phoneField));
                System.out.println("Первоначальная форма ввода скрыта");
            } catch (TimeoutException e) {
                System.out.println("Первоначальная форма ввода не скрылась");
            }

            boolean paymentFormAppeared = false;

            String[] paymentFormIndicators = {
                    "//h2[contains(text(), 'Оплата') or contains(text(), 'Payment')]",
                    "//input[contains(@id, 'card') or contains(@name, 'card')]",
                    "//button[contains(text(), 'Оплатить') or contains(text(), 'Pay')]",
                    "//div[contains(@class, 'payment-form')]"
            };

            for (String xpath : paymentFormIndicators) {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                    System.out.println("Найден элемент платежной формы: " + xpath);
                    paymentFormAppeared = true;
                    break;
                } catch (TimeoutException e) {
                    System.out.println("Элемент не найден: " + xpath);
                }
            }

            if (paymentFormAppeared) {
                System.out.println("Тест пройден: платежная форма отобразилась");
                Assert.assertTrue(true);
            } else {
                // Делаем скриншот для анализа
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                System.out.println("Текущий HTML страницы:");
                System.out.println(driver.getPageSource());

                Assert.fail("Платежная форма не отобразилась после нажатия кнопки");
            }

        } catch (Exception e) {
            System.err.println("Ошибка при выполнении теста: " + e.getMessage());
            Assert.fail("Тест завершился с ошибкой: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

