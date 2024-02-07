package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {

    @Before
    //this method will always execute before every scenario
    //because it has before tag which is pre condition
    public void start(){
        openBrowserAndLaunchApplication();
    }

    @After
    //this method will always execute after every scenario
    //because it has after tag which is post condition
    public void end(Scenario scenario){
        byte[] pic;
        //before closing the browser, i need to get the screenshot for the test
        //scenario class in cucumber, this class contains all the information about
        // the scenario you are executing
        if(scenario.isFailed()) {
            pic = takeScreenshot("failed/"+scenario.getName());
        }else{
            pic = takeScreenshot("passed/"+scenario.getName());
        }
        closeBrowser();
    }

}
