package Lesson16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageMts extends OpenPage {

    @FindBy(xpath = "//h2[contains(., 'Онлайн пополнение')]")
    private static WebElement onlineReplenishmentTitle;

    @FindBy(xpath = "//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement moreAboutServiceLink;

    @FindBy(id = "connection-phone")
    private WebElement phoneField;

    @FindBy(id = "connection-sum")
    private WebElement sumField;

    @FindBy(id = "connection-email")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='pay-connection']/button")
    private WebElement continueButton;

    @FindBy(css = "iframe.bepaid-iframe")
    private WebElement paymentFrame;

    public HomePageMts(WebDriver driver) {
        super(driver);
    }

    public static String getPageTitleText() {
        return onlineReplenishmentTitle.getText();
    }

    public void fillPhoneNumber(String phoneNumber) {
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
    }

    public void fillAmount(String amount) {
        sumField.clear();
        sumField.sendKeys(amount);
    }

    public void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public boolean waitForPaymentFrame() {
        try {
            wait.until(ExpectedConditions.visibilityOf(paymentFrame));
            return paymentFrame.getAttribute("src").contains("checkout.bepaid.by");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoVisible(PaySystemLogos logo) {
        try {
            WebElement logoElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(logo.xpath)));
            return logoElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getServiceLinkHref() {
        return moreAboutServiceLink.getAttribute("href");
    }

    public boolean isPaymentFrameLoaded() {
        return paymentFrame.getAttribute("src").contains("checkout.bepaid.by");
    }


}