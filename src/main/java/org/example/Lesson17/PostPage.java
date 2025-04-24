package org.example.Lesson17;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostPage extends BasePage {
    private static final String POST_ENDPOINT = "/post";

    public PostPage() {
        super();
        request.basePath(POST_ENDPOINT);
    }

    public PostPage withRawTextBody(String body) {
        request.contentType(ContentType.TEXT)
                .body(body);
        return this;
    }

    public PostPage withFormData(String key, String value) {
        request.contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam(key, value)
                .header("Accept-Charset", "UTF-8");
        return this;
    }

    public Response sendPostRequest() {
        return request.when().post();
    }
}
