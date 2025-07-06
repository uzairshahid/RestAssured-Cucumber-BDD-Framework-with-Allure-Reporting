package Utilities.RestAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
public class RestAssuredConnection {
    public static String baseURL;

    public static RequestSpecification getRequest() {
        RequestSpecification Request = null;
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setBaseUri(baseURL);
        builder.setContentType(ContentType.JSON);
        builder.setAccept("application/json, text/plain, */*");
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec).log().all();

        return Request;
    }

    public RestAssuredConnection(String base_api) {
        baseURL = base_api;
    }
}
