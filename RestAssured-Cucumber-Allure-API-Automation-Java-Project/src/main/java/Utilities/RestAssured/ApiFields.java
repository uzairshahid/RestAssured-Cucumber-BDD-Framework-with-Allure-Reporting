package Utilities.RestAssured;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
public class ApiFields {

    public static Response responseObject;
    public static int expectedResponseCode;
    public static String expectedResponseMessage;
    public static Map<String, Object> headerMap = new HashMap<>();
    public static JsonNode apiData;
    public static String apiBody;
    public static String endPoint;
}
