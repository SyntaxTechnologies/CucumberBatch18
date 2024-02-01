package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginSteps extends CommonMethods {
    //we don't need this driver, since it is coming from common methods class
  // public WebDriver driver;

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application(){
        openBrowserAndLaunchApplication();
       // driver = new ChromeDriver();
        //driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("user enters admin username and password")
    public void user_enters_admin_username_and_password() {
      // driver.findElement(By.id("txtUsername")).sendKeys("admin");
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        sendText(ConfigReader.read("userName"),usernameField);
        sendText(ConfigReader.read("password"),passwordField);
       //driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
       //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement loginButton =  driver.findElement(By.id("btnLogin"));
        click(loginButton);
        //driver.findElement(By.id("btnLogin")).click();
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        System.out.println("My test is passed");
    }

}
