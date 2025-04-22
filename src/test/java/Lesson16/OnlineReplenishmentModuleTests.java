package Lesson16;

import org.example.Lesson16.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OnlineReplenishmentModuleTests extends BaseTest {

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
