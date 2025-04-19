package Lesson15TestsBlockOnlineReplenishmentWithoutCommission;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class RefTest {

    @Test
    public void testRefMoreAboutService() {

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

            try {
                WebElement cookieBanner = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("div.cookie.show")));
                WebElement acceptButton = cookieBanner.findElement(By.xpath(".//button[contains(text(), 'Принять') or contains(text(), 'Согласен')]"));
                acceptButton.click();
                wait.until(ExpectedConditions.invisibilityOf(cookieBanner));
            } catch (Exception e) {
                System.out.println("Cookie banner не найден или не может быть закрыт");
            }

            WebElement refMoreAboutService = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")));

            Assert.assertTrue(refMoreAboutService.isDisplayed(), "Ссылка не отображается");
            Assert.assertTrue(refMoreAboutService.isEnabled(), "Ссылка неактивна");

            String expectedRef = "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
            String actualRef = refMoreAboutService.getAttribute("href");
            Assert.assertTrue(actualRef.contains(expectedRef), "Некорректный href у ссылки. Ожидалось: "
                    + expectedRef + ", фактически: " + actualRef);

            String initialUrl = driver.getCurrentUrl();
            refMoreAboutService.click();

            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(initialUrl)));

            Assert.assertFalse(driver.getTitle().contains("404"), "Страница не найдена (404)");
            Assert.assertFalse(driver.getPageSource().contains("Страница не найдена"),
                    " (Страница не найдена)");

            System.out.println("Ссылка 'Подробнее о сервисе' работает корректно");

        } catch (Exception e) {
            System.err.println("Ошибка при выполнении теста: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Тест завершился с ошибкой: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

}
