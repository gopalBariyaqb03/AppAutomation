package pages;

import io.appium.java_client.android.AndroidDriver;
import utils.CommonActions;
import utils.DriverManager;

public class BasePage {

    protected AndroidDriver driver;
    protected CommonActions commonActions;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.commonActions = new CommonActions(driver);
    }

    public String getCurrentActivity() {
        return driver.currentActivity();
    }

    public String getCurrentPackage() {
        return driver.getCurrentPackage();
    }

//    public void waitForPageToLoad() {
//        commonActions.waitForAppToLoad();
//    }

}