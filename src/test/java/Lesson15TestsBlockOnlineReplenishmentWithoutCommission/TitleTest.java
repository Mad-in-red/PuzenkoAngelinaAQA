package Lesson15TestsBlockOnlineReplenishmentWithoutCommission;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class TitleTest {


    @Test
    public void testTitle() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64;" +
                " x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 " +
                "Safari/537.36");
        options.setExperimentalOption("excludeSwitches", new
                String[]{"enable-automation"});
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);

        try{
            Thread.sleep(2000);

            driver.get("https://www.mts.by");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h2[contains(., 'Онлайн пополнение')]")));

            String fullText = titleElement.getText();
            if(fullText.contains("Онлайн пополнение") && fullText.contains("без комиссии")) {
                System.out.println("Текст верный: " + fullText);
            } else {
                System.out.println("Текст не соответствует ожидаемому: " + fullText);
            }

        } catch (Exception e) {
            System.err.println("Ошибка при выполнении теста: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }


}
