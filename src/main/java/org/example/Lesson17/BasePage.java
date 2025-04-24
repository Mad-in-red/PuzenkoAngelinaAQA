package org.example.Lesson17;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasePage {
    protected static final String BASE_URL = "https://postman-echo.com";

    protected RequestSpecification request;

    public BasePage() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
    }

    protected Response sendRequestAndGetResponse() {
        return request.when().get();
    }
}
