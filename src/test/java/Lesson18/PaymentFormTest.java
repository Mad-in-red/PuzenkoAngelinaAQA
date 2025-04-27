package Lesson18;

import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

@Epic("Проверка платежной формы")
@Feature("Корректность отображения данных")
public class PaymentFormTest extends BaseTest {

    @BeforeMethod
    public void prepareTest() {
        preparePaymentForm("Услуги связи", TEST_PHONE_NUMBER, TEST_SUM);
    }

    @Test(description = "Комплексная проверка платежной формы")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Пользователь видит корректные данные в платежной форме")
    @Description("Тест проверяет отображение номера телефона, суммы оплаты и плейсхолдеров полей")
    public void testPaymentFormDisplay() {
        try {
            Allure.step("Переключение на платежный фрейм", () -> {
                paymentFrame.switchToPaymentFrame();
            });

            SoftAssert softAssert = new SoftAssert();

            Allure.step("Проверка отображения номера телефона", () -> {
                String displayedPhone = paymentFrame.getPhoneNumber();
                String actualPhone = displayedPhone.replaceAll("[^0-9]", "");
                String expectedPhone = "375" + TEST_PHONE_NUMBER;

                softAssert.assertTrue(!displayedPhone.isEmpty(), "Номер телефона не отображается");
                softAssert.assertTrue(actualPhone.contains(expectedPhone),
                        String.format("Ожидался номер %s, но отображается %s", expectedPhone, actualPhone));

                Allure.addAttachment("Номер телефона", "text/plain",
                        "Ожидаемый: " + expectedPhone + "\nФактический: " + actualPhone);
            });

            Allure.step("Проверка суммы оплаты", () -> {
                String displayedAmount = paymentFrame.getPaymentAmount();
                softAssert.assertEquals(displayedAmount.trim(), TEST_SUM + ".00 BYN",
                        "Сумма платежа отображается некорректно");
                Allure.addAttachment("Сумма платежа", "text/plain", displayedAmount);
            });

            Allure.step("Проверка плейсхолдеров полей карты", () -> {
                softAssert.assertEquals(
                        paymentFrame.getFieldPlaceholder("Поле ввода номера карты"),
                        "Номер карты",
                        "Плейсхолдер для номера карты не совпадает");

                softAssert.assertEquals(
                        paymentFrame.getFieldPlaceholder("Поле ввода срока действия"),
                        "Срок действия",
                        "Плейсхолдер для срока действия не совпадает");

                softAssert.assertEquals(
                        paymentFrame.getFieldPlaceholder("Поле ввода CVC"),
                        "CVC",
                        "Плейсхолдер для CVC не совпадает");

                softAssert.assertEquals(
                        paymentFrame.getFieldPlaceholder("Поле имени держателя"),
                        "Имя держателя (как на карте)",
                        "Плейсхолдер для имени держателя не совпадает");
            });

            Allure.step("Проверка всех утверждений", () -> {
                softAssert.assertAll();
            });

        } catch (Exception e) {
            Allure.step("Ошибка при выполнении теста: " + e.getMessage(), () -> {
                takeScreenshot("error_screenshot");
                fail("Ошибка при проверке платежной формы: " + e.getMessage());
            });
        } finally {
            Allure.step("Возврат к основному контенту", paymentFrame::switchToDefaultContent);
        }
    }


    @DataProvider(name = "cardFields")
    public Object[][] cardFieldsData() {
        return new Object[][]{
                {"Поле ввода номера карты", "Номер карты"},
                {"Поле ввода срока действия", "Срок действия"},
                {"Поле ввода CVC", "CVC"},
                {"Поле имени держателя", "Имя держателя (как на карте)"}
        };
    }


}