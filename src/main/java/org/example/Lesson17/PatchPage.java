package org.example.Lesson17;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchPage extends BasePage {
    private static final String PATCH_ENDPOINT = "/patch";

    public PatchPage() {
        super();
        request.basePath(PATCH_ENDPOINT);
    }

    public PatchPage withRawTextBody(String body) {
        request.contentType(ContentType.TEXT)
                .body(body);
        return this;
    }

    public Response sendPatchRequest() {
        return request.when().patch();
    }
}
