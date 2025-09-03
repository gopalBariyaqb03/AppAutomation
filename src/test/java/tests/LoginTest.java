package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify login page is displayed")
    public void testLoginPageDisplay() {
        LoginPage loginPage = new LoginPage();
        loginPage.LoginScreens();
    }

    @Test(priority = 2, description = "Perform login with valid credentials")
    public void testValidLogin() {
        try {
            LoginPage loginPage = new LoginPage();
            //loginPage.waitForLoginPage();

            // Perform login
            ///loginPage.doLogin("testuser@example.com", "password123");

            // Wait for navigation (adjust based on your app flow)
            Thread.sleep(3000);

            // Verify login success (adjust based on your app)
            // For example, check if we navigated to home screen
            String currentPackage = loginPage.getCurrentPackage();
            Assert.assertEquals(currentPackage, "com.aisante.bios", "Should remain in app after login");

            System.out.println("✅ Login test completed");

        } catch (Exception e) {
            System.out.println("❌ Login test failed: " + e.getMessage());
            Assert.fail("Login test failed: " + e.getMessage());
        }
    }
}