package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkFlowSteps {

    String baseURI = RestAssured.baseURI
            = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;
    RequestSpecification request;
    Response response;
    public static String employee_id;


    @Given("a JWT bearer token is created")
    public void a_jwt_bearer_token_is_created() {
        //prepare the request
         request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body("{\n" +
                        "  \"email\": \"testbatch@test118.com\",\n" +
                        "  \"password\": \"Test@123\"\n" +
                        "}");

        //hit the endpoint/send the request
        Response response = request.when().post(APIConstants.GENERATE_TOKEN);

        //save the token in token variable
        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);

    }

    @Given("a request is prepared to create an employee using api call")
    public void a_request_is_prepared_to_create_an_employee_using_api_call() {
         request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                body("{\n" +
                        "  \"emp_firstname\": \"mario\",\n" +
                        "  \"emp_lastname\": \"germeen\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2004-08-05\",\n" +
                        "  \"emp_status\": \"permanent\",\n" +
                        "  \"emp_job_title\": \"QA Engineer\"\n" +
                        "}");
    }

    @When("a POST call is made to create the employee")
    public void a_post_call_is_made_to_create_the_employee() {
         response =  request.when().post(APIConstants.CREATE_EMPLOYEE);
    }

    @Then("the status code for this request should be {int}")
    public void the_status_code_for_this_request_should_be(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
        //this method is used to print the response in console
        response.prettyPrint();
    }

    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }
    @Then("the employee id {string} is stored as global variable")
    public void the_employee_id_is_stored_as_global_variable(String employeeId) {
        employee_id = response.jsonPath().getString(employeeId);
        System.out.println(employee_id);
    }

    //------------------------------------------------------------------------------------

    @Given("a request is prepared to get the created employee")
    public void a_request_is_prepared_to_get_the_created_employee() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY,token).
                queryParam("employee_id",employee_id);
    }

    @When("a GET call is made to retrieve the employee")
    public void a_get_call_is_made_to_retrieve_the_employee() {
         response = request.when().get(APIConstants.GET_ONE_EMPLOYEE);

    }

    @Then("the status code for this get request is {int}")
    public void the_status_code_for_this_get_request_is(Integer statusCode) {
        //validate the response
        response.then().assertThat().statusCode(statusCode);
        response.prettyPrint();
    }

    @Then("the employee has ID {string} must match with global emp id")
    public void the_employee_has_id_must_match_with_global_emp_id(String pathEmpId) {
        String temporaryEmpId =
                response.jsonPath().getString(pathEmpId);
        //here we are comparing both emp id's from get and post call
        Assert.assertEquals(temporaryEmpId, employee_id);
    }

    @Then("the data coming from {string} object should match with the data used in post call")
    public void the_data_coming_from_object_should_match_with_the_data_used_in_post_call
            (String empObject, io.cucumber.datatable.DataTable dataTable) {
        //data coming from feature file will be stored here
        List<Map<String,String>> expectedData = dataTable.asMaps();
        //data will be taken up from the response from employee object
        Map<String,String> actualData = response.jsonPath().get(empObject);

        //this is the time to compare the values
        for (Map<String,String> map:expectedData
             ) {
            //to get all the keys which are unique
            Set<String> keys   = map.keySet();
            for (String key:keys
                 ) {
                //this values one by one coming from feature file
                String expectedValue = map.get(key);
                //this value one by one coming from response i.e employee object
                String actualValue = actualData.get(key);
                Assert.assertEquals(actualValue, expectedValue);
            }
        }


    }



}
