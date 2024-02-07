package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on Add Employee option")
    public void user_clicks_on_add_employee_option() {
       // WebElement addEmployeeButton = driver.findElement(By.xpath("//*[@id='menu_pim_addEmployee']"));
        click(dashboardPage.addEmployeeOption);
    }

    @When("user enters firstName and middleName and lastName")
    public void user_enters_first_name_and_middle_name_and_last_name() {
     //   WebElement firstNameLoc = driver.findElement(By.xpath("//*[@id='firstName']"));
     //   WebElement middleNameLoc = driver.findElement(By.xpath("//*[@id='middleName']"));
    //    WebElement lastNameLoc = driver.findElement(By.xpath("//*[@id='lastName']"));

        sendText("nasima", addEmployeePage.firstNameLoc);
        sendText("ms", addEmployeePage.middleNameLoc);
        sendText("arbaz", addEmployeePage.lastNameLoc);
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        //WebElement saveButton = driver.findElement(By.xpath("//*[@id='btnSave']"));
        click(addEmployeePage.saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee Added");
    }


    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
      //  WebElement firstNameLoc = driver.findElement(By.xpath("//*[@id='firstName']"));
      //  WebElement middleNameLoc = driver.findElement(By.xpath("//*[@id='middleName']"));
      //  WebElement lastNameLoc = driver.findElement(By.xpath("//*[@id='lastName']"));

        sendText(firstName, addEmployeePage.firstNameLoc);
        sendText(middleName, addEmployeePage.middleNameLoc);
        sendText(lastName, addEmployeePage.lastNameLoc);

    }

    @When("user enters {string} and {string} and then {string}")
    public void user_enters_and_and_then(String firstname, String middleName, String lastName) {
      //  WebElement firstNameLoc = driver.findElement(By.xpath("//*[@id='firstName']"));
      //  WebElement middleNameLoc = driver.findElement(By.xpath("//*[@id='middleName']"));
      //  WebElement lastNameLoc = driver.findElement(By.xpath("//*[@id='lastName']"));

        sendText(firstname, addEmployeePage.firstNameLoc);
        sendText(middleName, addEmployeePage.middleNameLoc);
        sendText(lastName, addEmployeePage.lastNameLoc);
    }

    @When("user adds multiple employees from excel and verify the employee has added")
    public void user_adds_multiple_employees_from_excel_and_verify_the_employee_has_added() throws InterruptedException, IOException {
      //this excel reader returns list of maps

        List<Map<String,String>> newEmployees = ExcelReader.read();

        for (Map<String,String> newEmployee:newEmployees
             ) {
            //this newEmployee map will give me all the values based on the keys
            sendText(newEmployee.get("firstName"), addEmployeePage.firstNameLoc);
            sendText(newEmployee.get("middleName"), addEmployeePage.middleNameLoc);
            sendText(newEmployee.get("lastName"), addEmployeePage.lastNameLoc);
            sendText(newEmployee.get("Photograph"), addEmployeePage.photograph);

            if(!addEmployeePage.checkBox.isSelected()){
                click(addEmployeePage.checkBox);
            }
            sendText(newEmployee.get("Username"), addEmployeePage.username);
            sendText(newEmployee.get("Password"), addEmployeePage.password);
            sendText(newEmployee.get("confirmPassword"), addEmployeePage.confirmPassword);
           //to get the employee id, we need attribute of the element
            String employeeId = addEmployeePage.empIdLoc.getAttribute("value");
            click(addEmployeePage.saveButton);
            //validate the employee that it is added
            click(dashboardPage.addEmployeeOption);
            Thread.sleep(2000);

        }

    }

    @When("user add multiple employees from datatable and verify they are added")
    public void user_add_multiple_employees_from_datatable_and_verify_they_are_added
            (io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String,String>> employeeNames = dataTable.asMaps();
        for (Map<String,String> newEmployee:employeeNames){
            //enter the names of the employees from data table
            sendText(newEmployee.get("firstName"), addEmployeePage.firstNameLoc);
            sendText(newEmployee.get("middleName"), addEmployeePage.middleNameLoc);
            sendText(newEmployee.get("lastName"), addEmployeePage.lastNameLoc);
            //to get the employee id, we need attribute of the element
            String employeeId = addEmployeePage.empIdLoc.getAttribute("value");
            click(addEmployeePage.saveButton);
            Thread.sleep(2000);
            click(dashboardPage.empListOption);
            Thread.sleep(2000);
            //we need to search by id on this page
            sendText(employeeId, employeeSearchPage.empIdLoc);
            click(employeeSearchPage.searchOption);
            List<WebElement> rowData =
                    driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
            for (int i=0; i<rowData.size();i++){
                System.out.println("i am inside the loop now");
                String rowText = rowData.get(i).getText();
                //it will print the data from row
                System.out.println(rowText);
                String expectedData = employeeId + " "+newEmployee.get("firstName")+" "+
                        newEmployee.get("middleName")+" "+newEmployee.get("lastName");
                System.out.println(expectedData);
                Assert.assertEquals(rowText, expectedData);
            }
            Thread.sleep(2000);
            click(dashboardPage.addEmployeeOption);
        }

    }

}
