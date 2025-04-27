package Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class OnlineReplenishmentTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        setupDriver();
        driver.get("https://www.mts.by");
        try {
            closeCookieBanner();
        } catch (TimeoutException e) {
            System.out.println("Cookie banner не найден, продолжаем выполнение тестов");
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Уменьшил время ожидания
    }

    private void closeCookieBanner() {
        WebElement cookieBanner = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'cookie')]")));
        WebElement acceptButton = cookieBanner.findElement(
                By.xpath(".//button[contains(., 'Принять') or contains(., 'Согласен')]"));
        acceptButton.click();
        wait.until(ExpectedConditions.invisibilityOf(cookieBanner));
    }

    @Test
    public void verifyTitle() {
        WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[contains(., 'Онлайн пополнение')]")));
        String fullText = titleElement.getText();
        Assert.assertTrue(fullText.contains("Онлайн пополнение") && fullText.contains("без комиссии"),
                "Текст не соответствует ожидаемому: " + fullText);
    }

    @Test
    public void verifyPaymentSystemLogos() {
        List<WebElement> logoElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//*[@id='pay-section']//ul/li/img")));
        Assert.assertTrue(logoElements.size() >= 3, "Количество логотипов меньше ожидаемого");
    }

    @Test
    public void verifyMoreAboutServiceLink() {
        WebElement refMoreAboutService = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")));
        Assert.assertTrue(refMoreAboutService.isDisplayed(), "Ссылка не отображается");
        Assert.assertTrue(refMoreAboutService.isEnabled(), "Ссылка неактивна");

        String expectedRef = "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualRef = refMoreAboutService.getAttribute("href");
        Assert.assertTrue(actualRef.contains(expectedRef), "Некорректный href у ссылки");
    }


    @Test
    public void verifyContinueButtonFunctionality() {
        fillPhoneNumber("297777777");
        fillAmount("10");
        clickContinueButton();

        WebElement paymentFrame = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("iframe.bepaid-iframe")));

        Assert.assertTrue(paymentFrame.isDisplayed(), "Платежный iframe не отобразился");
    }

    private void fillPhoneNumber(String phoneNumber) {
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-phone")));
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
    }

    private void fillAmount(String amount) {
        WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-sum")));
        sumField.clear();
        sumField.sendKeys(amount);
    }

    private void clickContinueButton() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='pay-connection']/button")));
        continueButton.click();
    }
}
