package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on Add Employee option")
    public void user_clicks_on_add_employee_option() {
        WebElement addEmployeeButton = driver.findElement(By.xpath("//*[@id='menu_pim_addEmployee']"));
        click(addEmployeeButton);
    }

    @When("user enters firstName and middleName and lastName")
    public void user_enters_first_name_and_middle_name_and_last_name() {
        WebElement firstNameLoc = driver.findElement(By.xpath("//*[@id='firstName']"));
        WebElement middleNameLoc = driver.findElement(By.xpath("//*[@id='middleName']"));
        WebElement lastNameLoc = driver.findElement(By.xpath("//*[@id='lastName']"));

        sendText("nasima", firstNameLoc);
        sendText("ms",middleNameLoc);
        sendText("arbaz", lastNameLoc);
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        WebElement saveButton = driver.findElement(By.xpath("//*[@id='btnSave']"));
        click(saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee Added");
    }

}
