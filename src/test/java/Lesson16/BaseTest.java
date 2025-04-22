package Lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Lesson16.HomePageMts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

        protected WebDriver driver;
        protected HomePageMts homePageMts;

        @BeforeMethod
        public void setUp() {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            homePageMts = new HomePageMts(driver);
            driver.get("https://www.mts.by");
        }

        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

}
