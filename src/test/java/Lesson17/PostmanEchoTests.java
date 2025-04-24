package Lesson17;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.Lesson17.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostmanEchoTests extends BaseTest {
    private static final String COMMON_REQUEST_BODY = "This is expected to be sent back as part of response body.";

    @Test(priority = 1)
    public void testGetRequestWithQueryParams() {
        GetPage getPage = new GetPage();

        Response response = getPage
                .withQueryParam("foo1", "bar1")
                .withQueryParam("foo2", "bar2")
                .sendGetRequest();

        verifyStatusCode(response, 200);

        String foo1Value = response.jsonPath().getString("args.foo1");
        String foo2Value = response.jsonPath().getString("args.foo2");
        Assert.assertEquals(foo1Value, "bar1", "foo1 value should be 'bar1'");
        Assert.assertEquals(foo2Value, "bar2", "foo2 value should be 'bar2'");
    }

    @Test(priority = 2)
    public void testPostRequestWithRawText() {
        PostPage postPage = new PostPage();

        Response response = postPage
                .withRawTextBody(COMMON_REQUEST_BODY)
                .sendPostRequest();

        verifyStatusCode(response, 200);
        verifyResponseBodyContains(response, COMMON_REQUEST_BODY);
    }

    @Test(priority = 3)
    public void testPostRequestWithFormData() {
        PostPage postPage = new PostPage();

        Response response = postPage
                .withFormData("foo1", "bar1")
                .withFormData("foo2", "bar2")
                .sendPostRequest();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status 200 but got " + response.getStatusCode() +
                        "\nResponse body: " + response.getBody().asString());

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("form.foo1"), "bar1");
        Assert.assertEquals(jsonPath.getString("form.foo2"), "bar2");
    }

    @Test(priority = 4)
    public void testPutRequestWithRawText() {
        PutPage putPage = new PutPage();

        Response response = putPage
                .withRawTextBody(COMMON_REQUEST_BODY)
                .sendPutRequest();

        verifyStatusCode(response, 200);
        verifyResponseBodyContains(response, COMMON_REQUEST_BODY);
    }

    @Test(priority = 5)
    public void testPatchRequestWithRawText() {
        PatchPage patchPage = new PatchPage();

        Response response = patchPage
                .withRawTextBody(COMMON_REQUEST_BODY)
                .sendPatchRequest();

        verifyStatusCode(response, 200);
        verifyResponseBodyContains(response, COMMON_REQUEST_BODY);
    }

    @Test(priority = 6)
    public void testDeleteRequestWithRawText() {
        DeletePage deletePage = new DeletePage();

        Response response = deletePage
                .withRawTextBody(COMMON_REQUEST_BODY)
                .sendDeleteRequest();

        verifyStatusCode(response, 200);
        verifyResponseBodyContains(response, COMMON_REQUEST_BODY);
    }



}
