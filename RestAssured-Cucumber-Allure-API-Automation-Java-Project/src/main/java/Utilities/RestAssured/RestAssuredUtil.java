package Utilities.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class RestAssuredUtil {

    public static String getResponseNode(ResponseOptions response, String node) {
        JsonPath responsePath = response.getBody().jsonPath();
        if (responsePath.get(node) != null) {
            return responsePath.get(node).toString();
        }
        return null;
    }
    public static JSONArray getResponseArray(ResponseOptions response) {
        JSONArray array = new JSONArray(response.body().print());
        return array;
    }

    public static String getResponseObjectByKeyFromArray(ResponseOptions response, String key, String value, String get) {
        JSONArray array = getResponseArray(response);

        String result = "0";

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            if (object.getString(key).equalsIgnoreCase(value)) {
                result = String.valueOf(object.getInt(get));
                break;
            }
        }
        return result;
    }

    public static RequestSpecification getFormData(RequestSpecification request, JSONObject jsonObject) {
        Iterator<String> keys = jsonObject.keys();
        request.contentType("multipart/form-data");

        while (keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                request.formParam(key, jsonObject.get(key).toString());
            } else if (jsonObject.get(key) != null) {
                request.formParam(key, jsonObject.get(key));
            }
        }
        return request;
    }

    //    public static Response post_form_request( JSONObject api_body, String api_path) {
    public static Response post_form_request(Map<String, Object> api_headerMap, JSONObject api_body, String api_path) {
        RequestSpecification requestSpec = RestAssuredConnection.getRequest().accept("application/json").contentType("multipart/form-data");
        Set<Map.Entry<String, Object>> entries = api_headerMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            requestSpec.header(entry.getKey(), entry.getValue());
        }

        Iterator<String> Bodykeys = api_body.keys();
        while (Bodykeys.hasNext()) {
            String key = Bodykeys.next();
            if (api_body.get(key) instanceof JSONObject) {
                requestSpec.multiPart(key, api_body.get(key).toString());
            } else if (api_body.get(key) != null) {
                requestSpec.multiPart(key, api_body.get(key));
            }
        }
        return requestSpec.when().post(api_path).then().log().all().statusCode(200).extract().response();
    }


    public static Response post_request(RequestSpecification request, Map<String, Object> api_headerMap, String api_body, String api_path) {
        request.headers(api_headerMap);
        if (api_body != null) {
            request.body(api_body);
        }
        return request.post(api_path);
    }

    public static Response put_request(RequestSpecification request, Map<String, Object> api_headerMap, String api_body, String api_path) {
        request.headers(api_headerMap);
        request.body(api_body);
        return request.put(api_path);
    }

    public static Response get_request(RequestSpecification request, Map<String, Object> api_headerMap, String api_body, String api_path) {
        request.headers(api_headerMap);
        request.body(api_body);
        return request.get(api_path);
    }

    public static Response patch_request(RequestSpecification request, Map<String, Object> api_headerMap, String api_body, String api_path) {
        request.headers(api_headerMap);
        request.body(api_body);
        return request.patch(api_path);
    }

    public static Response delete_request(RequestSpecification request, Map<String, Object> api_headerMap, String api_body, String api_path) {
        request.headers(api_headerMap);
        request.body(api_body);
        return request.delete(api_path);
    }

    public static Response acquireCheckInAPILock(RequestSpecification request, Map<String, Object> headerMap, String apiBody, String endPoint, Response responseObject) {
        boolean lockNotAcquired = false;
        long currentTime = System.currentTimeMillis();
        long endTime = currentTime + 15000;
        do {
            //API call
            request = RestAssuredConnection.getRequest();
            responseObject = RestAssuredUtil.put_request(request, headerMap, apiBody, endPoint);

            if (RestAssuredUtil.getResponseNode(responseObject, "message") != null) {
                lockNotAcquired = true;
            } else if (RestAssuredUtil.getResponseNode(responseObject, "message") == null) {
                lockNotAcquired = false;
            }
        } while (lockNotAcquired && System.currentTimeMillis() < endTime);
        return responseObject;
    }
}
