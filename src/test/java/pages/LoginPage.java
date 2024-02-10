package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginPage extends CommonMethods {

    @FindBy(id="txtUsername")
    public WebElement usernameField;

    @FindBy(id="txtPassword")
    public WebElement passwordField;

    @FindBy(xpath="//*[@id='btnLogin']")
    public WebElement loginButton;

    @FindBy(id="spanMessage")
    public WebElement errorMessageLoc;

    public void enterUserName(){
        sendText(ConfigReader.read("username"),login.usernameField);
    }

    public void enterPassword(){
        sendText(ConfigReader.read("password"),login.passwordField);
    }

    public void clickLoginButton(){
        click(login.loginButton);
    }

    public LoginPage(){
        //page factory we use to initialize all the web elements and driver we pass as
        // an argument to it. this keyword means, we need to initialize all the elements
        // of this local class
        PageFactory.initElements(driver,this);
    }

}
