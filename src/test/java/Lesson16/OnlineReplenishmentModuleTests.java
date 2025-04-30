package Lesson16;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OnlineReplenishmentModuleTests extends BaseTest {

    private SelectService serviceForm;
    private HomePageMts homePageMts;

    @BeforeMethod
    public void prepareTest() {
        driver.navigate().refresh();
        acceptCookies();
        serviceForm = new SelectService(driver);
        homePageMts = new HomePageMts(driver);
    }

    @Test(dataProvider = "placeholderData")
    public void testPlaceholdersForAllServiceTypes(String serviceType, String fieldType,
                                                   String expectedPlaceholder) {
        serviceForm.selectService(serviceType);

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")),
                ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")),
                ExpectedConditions.visibilityOfElementLocated(By.id("connection-email"))
        ));

        String actualPlaceholder = serviceForm.getFieldPlaceholder(serviceType, fieldType);
        Assert.assertEquals(actualPlaceholder, expectedPlaceholder,
                String.format("Неверный плейсхолдер для поля %s в услуге %s", fieldType, serviceType));
    }

    @DataProvider(name = "placeholderData")
    public Object[][] placeholderData() {
        return new Object[][] {
                {"Услуги связи", "phone", "Номер телефона"},
                {"Услуги связи", "sum", "Сумма"},
                {"Услуги связи", "email", "E-mail для отправки чека"},

                {"Домашний интернет", "phone", "Номер абонента"},
                {"Домашний интернет", "sum", "Сумма"},
                {"Домашний интернет", "email", "E-mail для отправки чека"},

                {"Рассрочка", "account", "Номер счета на 44"},
                {"Рассрочка", "sum", "Сумма"},
                {"Рассрочка", "email", "E-mail для отправки чека"},

                {"Задолженность", "account", "Номер счета на 2073"},
                {"Задолженность", "sum", "Сумма"},
                {"Задолженность", "email", "E-mail для отправки чека"}
        };
    }

    @Test
    public void testPageTitle() {
        String title = homePageMts.getPageTitleText();
        Assert.assertTrue(title.contains("Онлайн пополнение") &&
                        title.contains("без комиссии"),
                "Некорректный заголовок: " + title);
    }

    @Test
    public void testPaymentLogos() {
        for (Lesson16.PaySystemLogos logo : Lesson16.PaySystemLogos.values()) {
            Assert.assertTrue(homePageMts.isLogoVisible(logo),
                    "Логотип " + logo.name + " не отображается");
        }
    }

    @Test
    public void testServiceLink() {
        String href = homePageMts.getServiceLinkHref();
        Assert.assertTrue(href.contains(
                        "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Некорректная ссылка: " + href);
    }

    @Test
    public void testPaymentForm() {
        preparePaymentForm("Услуги связи", "297777777", "500");
        boolean isFrameLoaded = homePageMts.waitForPaymentFrame();
        takeScreenshot("Платежная форма");
        Assert.assertTrue(isFrameLoaded, "Платежная форма не загрузилась");
    }
}