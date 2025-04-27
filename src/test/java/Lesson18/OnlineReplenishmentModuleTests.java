package Lesson18;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

@Epic("Тесты модуля онлайн-пополнения")
@Feature("Проверка функционала пополнения")
public class OnlineReplenishmentModuleTests extends BaseTest {

    @BeforeMethod
    public void prepareTest() {
        driver.navigate().refresh();
        acceptCookies();
    }

    @Test(dataProvider = "placeholderData")
    @Story("Проверка плейсхолдеров")
    @Description("Тест проверяет корректность плейсхолдеров для разных типов услуг")
    @Severity(SeverityLevel.NORMAL)
    public void testPlaceholdersForAllServiceTypes(String serviceType, String fieldType,
                                                   String expectedPlaceholder) {
        try {
            Allure.step("Выбираем услугу: " + serviceType, () -> {
                serviceForm.selectService(serviceType);

                wait.until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")),
                        ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")),
                        ExpectedConditions.visibilityOfElementLocated(By.id("connection-email"))
                ));
            });

            String actualPlaceholder = Allure.step("Получаем плейсхолдер для поля " + fieldType, () -> {
                String placeholder = serviceForm.getFieldPlaceholder(serviceType, fieldType);
                System.out.println("Service: " + serviceType + ", Field: " + fieldType +
                        ", Placeholder: " + placeholder);
                return placeholder;
            });

            Allure.step("Проверяем соответствие плейсхолдера", () -> {
                Assert.assertEquals(actualPlaceholder, expectedPlaceholder,
                        String.format("Неверный плейсхолдер для поля %s в услуге %s", fieldType, serviceType));
            });

        } catch (Exception e) {
            takeScreenshot("placeholder_error_" + serviceType + "_" + fieldType);
            throw new RuntimeException("Ошибка при проверке плейсхолдера для " + serviceType +
                    "/" + fieldType, e);
        }
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

    @Attachment
    public String[][] getPlaceholderTestData() {
        return (String[][]) placeholderData();
    }


    @Test
    @Story("Проверка заголовка")
    @Description("Тест проверяет корректность заголовка модуля онлайн-пополнения")
    @Severity(SeverityLevel.NORMAL)
    public void testPageTitle() {
        String title = Allure.step("Получаем название заголовка", () -> {
            return homePageMts.getPageTitleText();
        });

        Allure.step("Проверяем корректность заголовка", () -> {
            Assert.assertTrue(title.contains("Онлайн пополнение") &&
                            title.contains("без комиссии"),
                    "Некорректный заголовок: " + title);
        });

    }



    @Test
    @Story("Проверка логотипов платежных систем")
    @Description("Тест проверяет наличие логотипов платежных систем")
    @Severity(SeverityLevel.NORMAL)
    public void testPaymentLogos() {

        Allure.step("Проверяем наличие логотипов платежных систем", () -> {
            for (PaySystemLogos logo : PaySystemLogos.values()) {
                Assert.assertTrue(homePageMts.isLogoVisible(logo),
                        "Логотип " + logo.name + " не отображается");
            }
        });


    }

    @Test
    @Story("Проверка информационной ссылки")
    @Description("Тест проверяет корректность ссылки и открываемость информационной ссылки")
    @Severity(SeverityLevel.NORMAL)
    public void testServiceLink() {
        String href = Allure.step("Получаем ссылку", () ->{
            return homePageMts.getServiceLinkHref();
        });

        Allure.step("Проверяем корректность ссылки", () -> {
            Assert.assertTrue(href.contains(
                            "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                    "Некорректная ссылка: " + href);
        });

    }

   @Test
   @Story("Проверка работы кнопки продолжить")
   @Description("Тест проверяет корректность работы кнопки продолжить")
   @Severity(SeverityLevel.CRITICAL)
   public void testPaymentForm() {

       Allure.step("Подготовка платежной формы", () -> {
           preparePaymentForm("Услуги связи", "297777777", "500");
       });

       boolean isFrameLoaded = Allure.step("Ожидаем загрузку платежной формы", () -> {
           return homePageMts.waitForPaymentFrame();
       });

       takeScreenshot("Платежная форма");

       Assert.assertTrue(isFrameLoaded, "Платежная форма не загрузилась");
   }

}
