package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Page Elements
    public String NEXTBTN = "//android.widget.TextView[@text=\"Next\"]";
    public String GETSTARTEDBTN = "//android.widget.TextView[@text=\"Get Started\"]";
    public String EMAILINP = "//android.widget.EditText[@text=\"E-mail\"]";
    public String PASSWORDINP = "//android.widget.EditText[@text=\"Password\"]";
    public String SIGNUPBTN = "//android.widget.TextView[@text=\"Sign In\"]";
    public String COMPANYINP = "//android.widget.EditText[@text=\"Company Code\"]";
    public String SUBMITBTN = "//android.view.ViewGroup[@content-desc=\"Submit\"]";


    public void LoginScreens(){

        commonActions.logPrint("step:: Click on the next button");
        commonActions.waitForElement(By.xpath(NEXTBTN));
        commonActions.click(By.xpath(NEXTBTN));

        commonActions.logPrint("step:: Click on the next button");
        commonActions.waitForElement(By.xpath(NEXTBTN));
        commonActions.click(By.xpath(NEXTBTN));

        commonActions.logPrint("step:: Click on the next button");
        commonActions.waitForElement(By.xpath(NEXTBTN));
        commonActions.click(By.xpath(NEXTBTN));

        commonActions.logPrint("step:: Click on the Get Started button");
        commonActions.waitForElement(By.xpath(GETSTARTEDBTN));
        commonActions.click(By.xpath(GETSTARTEDBTN));

        commonActions.logPrint("Step:: Enter the Company code");
        commonActions.waitForElement(By.xpath(COMPANYINP));
        commonActions.type(By.xpath(COMPANYINP), "NWB27XHH79");

        commonActions.logPrint("step:: Click on the Submit button");
        commonActions.waitForElement(By.xpath(SUBMITBTN));
        commonActions.click(By.xpath(SUBMITBTN));

        commonActions.logPrint("step:: Enter the email");
        commonActions.waitForElement(By.xpath(EMAILINP));
        commonActions.type(By.xpath(EMAILINP), "vrindamr@yopmail.com");

        commonActions.logPrint("step:: Enter the password");
        commonActions.waitForElement(By.xpath(PASSWORDINP));
        commonActions.type(By.xpath(PASSWORDINP), "Admin@123");

        commonActions.logPrint("step:: Click on the Sign In button");
        commonActions.waitForElement(By.xpath(SIGNUPBTN));
        commonActions.click(By.xpath(SIGNUPBTN));

    }
}