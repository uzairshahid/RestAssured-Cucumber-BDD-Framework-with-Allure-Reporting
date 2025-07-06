package Commons;

import Utilities.RestAssured.JSONReader;
import Utilities.RestAssured.RestAssuredConnection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;


    public class CommonSteps {
        public static final String testEnvironmentFilePath = "src/test/resources/TestData/Environment.json";

        @Given("Setting RestAssured connection for {string}")
        public static void settingRestAssuredConnectionFor(String envURL) throws IOException {

//            new RestAssuredConnection(JSONReader.jsonEnvNodeReader(testEnvironmentFilePath, System.getProperty("env"), envURL));
            new RestAssuredConnection("https://api.genderize.io");

        }



}
