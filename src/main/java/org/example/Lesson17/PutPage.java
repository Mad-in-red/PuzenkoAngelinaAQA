package org.example.Lesson17;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutPage extends BasePage {
    private static final String PUT_ENDPOINT = "/put";

    public PutPage() {
        super();
        request.basePath(PUT_ENDPOINT);
    }

    public PutPage withRawTextBody(String body) {
        request.contentType(ContentType.TEXT)
                .body(body);
        return this;
    }

    public Response sendPutRequest() {
        return request.when().put();
    }
}
