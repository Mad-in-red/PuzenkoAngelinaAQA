package Lesson16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SelectService extends OpenPage {

    public void fillPaymentData(String phoneNumber, String sum, String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-phone")));
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);

        WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-sum")));
        sumField.clear();
        sumField.sendKeys(sum);

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("connection-email")));
        emailField.clear();
        emailField.sendKeys(email);
    }

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button")
    private WebElement serviceSelectButton;

    private static final String SERVICE_OPTION_XPATH =
            "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[%d]/p";

    private final Map<String, String> serviceFieldPrefixes = new HashMap<>();

    public SelectService(WebDriver driver) {
        super(driver);
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver не может быть null");
        }

        serviceFieldPrefixes.put("Услуги связи", "connection");
        serviceFieldPrefixes.put("Домашний интернет", "internet");
        serviceFieldPrefixes.put("Рассрочка", "instalment");
        serviceFieldPrefixes.put("Задолженность", "arrears");
    }

    public void selectService(String serviceName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.elementToBeClickable(serviceSelectButton)).click();

        switch (serviceName) {
            case "Услуги связи":
                selectServiceOption(1);
                break;
            case "Домашний интернет":
                selectServiceOption(2);
                break;
            case "Рассрочка":
                selectServiceOption(3);
                break;
            case "Задолженность":
                selectServiceOption(4);
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип услуги: " + serviceName);
        }

        By formLocator = By.xpath("//*[@id='pay-section']/div/div/div[2]/section");

        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(formLocator));

    }

    private void selectServiceOption(int optionNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = String.format(SERVICE_OPTION_XPATH, optionNumber);
        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        option.click();
    }

    public String getFieldPlaceholder(String serviceType, String fieldType) {
        String prefix = serviceFieldPrefixes.get(serviceType);
        if (prefix == null) {
            throw new IllegalArgumentException("Неизвестный тип услуги: " + serviceType);
        }

        String fieldId;
        switch (fieldType) {
            case "phone":
                fieldId = prefix + "-phone";
                break;
            case "account":
                fieldId = prefix.equals("instalment") ? "score-instalment" :
                        prefix.equals("arrears") ? "score-arrears" : prefix + "-phone";
                break;
            case "sum":
                fieldId = prefix + "-sum";
                break;
            case "email":
                fieldId = prefix + "-email";
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип поля: " + fieldType);
        }

        try {
            System.out.println("[DEBUG] Ищем поле с ID: " + fieldId + " для услуги " + serviceType);
            WebElement field = driver.findElement(By.id(fieldId));
            String placeholder = field.getAttribute("placeholder");
            System.out.println("[DEBUG] Найденный плейсхолдер: '" + placeholder + "'");
            return placeholder;
        } catch (Exception e) {
            System.out.println("[ERROR] Не удалось найти поле с ID: " + fieldId);
            throw new RuntimeException("Не удалось найти поле с ID: " + fieldId +
                    " для услуги " + serviceType, e);
        }

    }


}



