package Lesson17;

import io.restassured.response.Response;
import org.testng.Assert;

public class BaseTest {
    protected void verifyStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Status code should be " + expectedStatusCode);
    }

    protected void verifyResponseBodyContains(Response response, String expectedText) {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains(expectedText),
                "Response should contain: " + expectedText);
    }
}
