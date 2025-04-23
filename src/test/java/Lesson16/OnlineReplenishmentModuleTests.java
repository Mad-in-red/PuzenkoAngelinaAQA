package Lesson16;

import org.example.Lesson16.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OnlineReplenishmentModuleTests extends BaseTest {

    @Test(dataProvider = "placeholderData")
    public void testPlaceholdersForAllServiceTypes(String serviceType, String fieldType, String expectedPlaceholder) {

        serviceForm.selectService(serviceType);
        System.out.println("Услуга выбрана: " + serviceType);

        try {
            String actualPlaceholder = serviceForm.getFieldPlaceholder(serviceType, fieldType);
            System.out.println("Плейсхолдер получен: " + actualPlaceholder);

            Assert.assertEquals(actualPlaceholder, expectedPlaceholder,
                    String.format("Неверный плейсхолдер для поля %s в услуге %s", fieldType, serviceType));
        } catch (Exception e) {
            System.out.println("Ошибка в тесте: " + e.getMessage());
            throw e;
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


    @Test
    public void testPageTitle() {
        String title = homePageMts.getPageTitleText();
        Assert.assertTrue(title.contains("Онлайн пополнение") &&
                        title.contains("без комиссии"),
                "Некорректный заголовок: " + title);
    }

    @Test
    public void testPaymentLogos() {
        for (PaySystemLogos logo : PaySystemLogos.values()) {
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
        homePageMts.fillPhoneNumber("297777777");
        homePageMts.fillAmount("500");
        homePageMts.clickContinueButton();

        boolean isFrameLoaded = homePageMts.waitForPaymentFrame();
        Assert.assertTrue(isFrameLoaded, "Платежная форма не загрузилась");
    }

}
