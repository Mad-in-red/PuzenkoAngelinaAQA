package Lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Lesson16.HomePageMts;
import org.example.Lesson16.SelectService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected HomePageMts homePageMts;
    protected SelectService serviceForm;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        homePageMts = new HomePageMts(driver);
        serviceForm = new SelectService(driver);
        driver.get("https://www.mts.by");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(., 'Принять') or contains(., 'Согласен')]")
            ));
            cookieAccept.click();
            wait.until(ExpectedConditions.invisibilityOf(cookieAccept));
        } catch (Exception e) {
            System.out.println("Куки-баннер не найден или уже закрыт");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void closeCookieBanner() {
        try {
            String acceptCookiesButton = "//button[contains(text(), 'Принять') or contains(text()," +
                    " 'Согласен')]";
            driver.findElement(By.xpath(acceptCookiesButton)).click();
        } catch (Exception e) {
            System.out.println("Cookie banner не найден или не может быть закрыт");
        }
    }

}
