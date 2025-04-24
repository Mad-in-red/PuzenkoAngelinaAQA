package org.example.Lesson17;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeletePage extends BasePage {
    private static final String DELETE_ENDPOINT = "/delete";

    public DeletePage() {
        super();
        request.basePath(DELETE_ENDPOINT);
    }

    public DeletePage withRawTextBody(String body) {
        request.contentType(ContentType.TEXT)
                .body(body);
        return this;
    }

    public Response sendDeleteRequest() {
        return request.when().delete();
    }
}
