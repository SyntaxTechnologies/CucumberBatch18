package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {

    @FindBy(id="firstName")
    public WebElement firstNameLoc;

    @FindBy(id="middleName")
    public WebElement middleNameLoc;

    @FindBy(id="lastName")
    public WebElement lastNameLoc;

    @FindBy(id="btnSave")
    public WebElement saveButton;

    @FindBy(id="photofile")
    public WebElement photograph;

    @FindBy(id="user_name")
    public WebElement username;


    @FindBy(id="user_password")
    public WebElement password;

    @FindBy(id="re_password")
    public WebElement confirmPassword;

    @FindBy(id="chkLogin")
    public WebElement checkBox;

    @FindBy(id="employeeId")
    public WebElement empIdLoc;


    public AddEmployeePage(){
        PageFactory.initElements(driver, this);
    }

}
