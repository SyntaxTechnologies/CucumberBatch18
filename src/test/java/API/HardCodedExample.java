package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExample {

    String baseURI = RestAssured.baseURI
            = "http://hrm.syntaxtechs.net/syntaxapi/api";
    static String employee_id;
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MTA2ODgxOTYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTcxMDczMTM5NiwidXNlcklkIjoiNjQ1MiJ9.PsOK1XpInWKd19jSgMvEKEm3OHckYLvoX0eRcstgRJk";


    @Test
    public void acreateEmployee(){
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
        //hamcrest matchers
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname",equalTo("mario"));
        response.then().assertThat().body("Employee.emp_lastname",equalTo("germeen"));
        response.then().assertThat().header("Connection",equalTo("Keep-Alive"));
        //to fetch the employee id from response body, we need response variable
        employee_id = response.jsonPath().getString("Employee.employee_id");
    }

    @Test
    public void bgetCreatedEmployee(){
        //prepare the request
        RequestSpecification request = given().
                header("Content-Type","application/json").
                header("Authorization",token).
                queryParam("employee_id",employee_id);


        //hitting the endpoint
        Response response = request.when().get("/getOneEmployee.php");
        //validate the response
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        String temporaryEmpId =
                response.jsonPath().getString("employee.employee_id");
        //here we are comparing both emp id's from get and post call
        Assert.assertEquals(temporaryEmpId, employee_id);
        //validate the body from get call
        response.then().assertThat().
                body("employee.emp_lastname",equalTo("germeen"));
        response.then().assertThat().
                body("employee.emp_firstname",equalTo("mario"));
    }

    @Test
    public void cUpdateEmployee(){
        //prepare the request
        RequestSpecification request = given().
                header("Content-Type","application/json").
                header("Authorization",token).
                body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"thomas\",\n" +
                        "  \"emp_lastname\": \"kartal\",\n" +
                        "  \"emp_middle_name\": \"hobbes\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2014-03-09\",\n" +
                        "  \"emp_status\": \"doubtful\",\n" +
                        "  \"emp_job_title\": \"philosopher\"\n" +
                        "}");

        //hitting the endpoint
        Response response = request.when().put("/updateEmployee.php");
        //validation of response
        response.then().assertThat().statusCode(200);
        response.then().assertThat().
                body("Message",equalTo("Employee record Updated"));
        response.prettyPrint();
    }

    @Test
    public void dGetUpdatedEmployee(){
        //prepare the request
        RequestSpecification request = given().
                header("Content-Type","application/json").
                header("Authorization", token).
                queryParam("employee_id",employee_id);
        //hitting the endpoint
        Response response = request.when().get("/getOneEmployee.php");
        //validation
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

}
