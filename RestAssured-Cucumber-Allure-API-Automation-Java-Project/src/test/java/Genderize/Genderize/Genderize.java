package Genderize.Genderize;

import Genderize.GenderizeAPIResources;
import Utilities.RestAssured.ApiFields;
import Utilities.RestAssured.RestAssuredConnection;
import Utilities.RestAssured.RestAssuredUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;

public class Genderize implements GenderizeAPIResources {
    private static RequestSpecification request = null;


    @Then("User should be able to search by name as {string}")
    public void user_should_be_able_to_search_by_name_as(String name) {
        if (ApiFields.apiData == null) {
            ApiFields.apiData = new ObjectMapper().createObjectNode();
        }

        ApiFields.headerMap.clear();
        ApiFields.headerMap.put("secretkey", "93cda8ef7af667ba43b028b1e48f7ab4");
//        ApiFields.endPoint = "?name=" + name;
        ApiFields.endPoint =String.format(GenderizeAPIResources.GET_USER_BY_NAME,name);

        request = RestAssuredConnection.getRequest();
        ApiFields.responseObject = RestAssuredUtil.get_request(request, ApiFields.headerMap, "{}", ApiFields.endPoint);

        try {
            ApiFields.apiData = new ObjectMapper().readTree(ApiFields.responseObject.asString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse API response", e);
        }

        ApiFields.responseObject.then()
                .log().all()
                .statusCode(200);
    }


    @Then("user should be able to do classification in the scope of a specific country And Name as {string} And {string}")
    public void user_should_be_able_to_do_classification_in_the_scope_of_a_specific_country_as(String name,String countryName) {
        if (ApiFields.apiData == null) {
            ApiFields.apiData = new ObjectMapper().createObjectNode();
        }

        ApiFields.headerMap.clear();
        ApiFields.headerMap.put("secretkey", "93cda8ef7af667ba43b028b1e48f7ab4");
        ApiFields.endPoint =String.format(GenderizeAPIResources.GET_USER_BY_NAME_AND_COUNTRY,name,countryName);

        request = RestAssuredConnection.getRequest();
        ApiFields.responseObject = RestAssuredUtil.get_request(request, ApiFields.headerMap, "{}", ApiFields.endPoint);

        try {
            ApiFields.apiData = new ObjectMapper().readTree(ApiFields.responseObject.asString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse API response", e);
        }

        ApiFields.responseObject.then()
                .log().all()
                .statusCode(200);
    }


    @Then("user should be able to check genders of multiple users {string} with single request")
    public void user_Should_Be_Able_To_Check_Genders_Of_Multiple_Users_With_Single_Request(String names) {
        if (ApiFields.apiData == null) {
            ApiFields.apiData = new ObjectMapper().createObjectNode();
        }

        String[] nameArray = names.split(",");

        StringBuilder endpointBuilder = new StringBuilder("?");
        for (String name : nameArray) {
            endpointBuilder.append("name[]=").append(name.trim()).append("&");
        }

        ApiFields.headerMap.clear();
        ApiFields.headerMap.put("secretkey", "93cda8ef7af667ba43b028b1e48f7ab4");
        ApiFields.endPoint = endpointBuilder.toString().replaceAll("&$", "");
//        ApiFields.endPoint = "?name[]=peter&name[]=lois&name[]=stewie";


        request = RestAssuredConnection.getRequest();
        ApiFields.responseObject = RestAssuredUtil.get_request(request, ApiFields.headerMap, "{}", ApiFields.endPoint);

        try {
            ApiFields.apiData = new ObjectMapper().readTree(ApiFields.responseObject.asString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse API response", e);
        }

        ApiFields.responseObject.then()
                .log().all()
                .statusCode(200);

    }
}
