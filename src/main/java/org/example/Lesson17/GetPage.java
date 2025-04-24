package org.example.Lesson17;

import io.restassured.response.Response;

public class GetPage extends BasePage {
    private static final String GET_ENDPOINT = "/get";

    public GetPage() {
        super();
        request.basePath(GET_ENDPOINT);
    }

    public GetPage withQueryParam(String paramName, String paramValue) {
        request.queryParam(paramName, paramValue);
        return this;
    }

    public Response sendGetRequest() {
        return sendRequestAndGetResponse();
    }

}