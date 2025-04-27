package Lesson18;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected HomePageMts homePageMts;
    protected SelectService serviceForm;
    protected PaymentFrame paymentFrame;

    protected static final String TEST_PHONE_NUMBER = "297777777";
    protected static final String TEST_SUM = "10";
    protected static final String TEST_EMAIL = "test@example.com";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-blink-features=AutomationControlled",
                "--start-maximized",
                "--disable-notifications"
        );
        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        this.homePageMts = new HomePageMts(driver);
        this.serviceForm = new SelectService(driver);
        this.paymentFrame = new PaymentFrame(driver);

        driver.get("https://www.mts.by");
        acceptCookies();
    }

    protected void acceptCookies() {
        try {
            WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(., 'Принять') or contains(., 'Согласен')]")));
            cookieAccept.click();
            wait.until(ExpectedConditions.invisibilityOf(cookieAccept));
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already closed: " + e.getMessage());
        }
    }

    protected void preparePaymentForm(String serviceType, String phone, String amount) {
        serviceForm.selectService(serviceType);
        homePageMts.fillPhoneNumber(phone);
        homePageMts.fillAmount(amount);
        homePageMts.clickContinueButton();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.cssSelector("iframe.bepaid-iframe")));
        driver.switchTo().defaultContent();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result.getName());
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    protected void takeScreenshot(String testName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(testName, new ByteArrayInputStream(screenshot).toString(), "image/png");
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}