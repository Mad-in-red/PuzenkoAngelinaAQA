package Lesson15TestsBlockOnlineReplenishmentWithoutCommission;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Lesson15.PaySystemLogo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class PaySystemLogoTests {

    @Test
    public void testPaymentSystemLogos() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.mts.by");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            List<PaySystemLogo> logosToVerify = PaySystemLogo.getPaymentSystemLogosToVerify();

            for (PaySystemLogo logo : logosToVerify) {
                verifyLogoPresence(driver, wait, logo.name, logo.xpath);
            }

        } catch (Exception e) {
            System.err.println("Ошибка при выполнении теста: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Тест завершился с ошибкой: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    private void verifyLogoPresence(WebDriver driver, WebDriverWait wait, String logoName, String xpath) {
        try {
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            Assert.assertTrue(logo.isDisplayed(), "Логотип " + logoName + " не отображается");
            System.out.println("Логотип " + logoName + " успешно проверен");
        } catch (Exception e) {
            Assert.fail("Не удалось найти логотип " + logoName + ": " + e.getMessage());
        }
    }


}