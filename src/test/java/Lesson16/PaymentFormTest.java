package Lesson16;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PaymentFormTest extends BaseTest {

    @Test(description = "Комплексная проверка платежной формы")
    public void testPaymentFormDisplay() {

       preparePaymentForm("Услуги связи", TEST_PHONE_NUMBER, TEST_SUM);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.cssSelector("iframe.bepaid-iframe")));

        SoftAssert softAssert = new SoftAssert();


        String displayedPhone = paymentFrame.getPhoneNumber();
        String actualPhone = displayedPhone.replaceAll("[^0-9]", "");
        String expectedPhone = "375" + TEST_PHONE_NUMBER;

        softAssert.assertTrue(!displayedPhone.isEmpty(), "Номер телефона не отображается");
        softAssert.assertEquals(actualPhone, expectedPhone,
                String.format("Ожидался номер %s, но отображается %s", expectedPhone, actualPhone));

        String displayedAmount = paymentFrame.getPaymentAmount();
        softAssert.assertEquals(displayedAmount.trim(), TEST_SUM + ".00 BYN",
                "Сумма платежа отображается некорректно");

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

        softAssert.assertAll();
        driver.switchTo().defaultContent();
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