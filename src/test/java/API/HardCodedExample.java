package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class HardCodedExample {

    String baseURI = RestAssured.baseURI
            = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MTA2ODgxOTYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTcxMDczMTM5NiwidXNlcklkIjoiNjQ1MiJ9.PsOK1XpInWKd19jSgMvEKEm3OHckYLvoX0eRcstgRJk";

    @Test
    public void createEmployee(){
        //it will create the request
        RequestSpecification request = given().
                    header("Content-Type","application/json").
                    header("Authorization",token).
                    body("{\n" +
                            "  \"emp_firstname\": \"mario\",\n" +
                            "  \"emp_lastname\": \"germeen\",\n" +
                            "  \"emp_middle_name\": \"ms\",\n" +
                            "  \"emp_gender\": \"M\",\n" +
                            "  \"emp_birthday\": \"2004-08-05\",\n" +
                            "  \"emp_status\": \"permanent\",\n" +
                            "  \"emp_job_title\": \"QA Engineer\"\n" +
                            "}");

        //it will give us the response after hitting the endpoint
        Response response =  request.when().post("/createEmployee.php");

        //assertthat is the method we use to validate the response
        response.then().assertThat().statusCode(201);
        //this method is used to print the response in console
        response.prettyPrint();
    }






}
