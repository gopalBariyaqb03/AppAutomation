package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DriverManager;

public class AppLaunchTest extends BaseTest {

    @Test(priority = 1, description = "Verify app launches successfully")
    public void testAppLaunch() {
        try {
            // Verify driver is initialized
            Assert.assertNotNull(DriverManager.getDriver(), "Driver should not be null");

            // Verify app is launched by checking if driver session exists
            String sessionId = DriverManager.getDriver().getSessionId().toString();
            Assert.assertNotNull(sessionId, "Session ID should not be null");

            System.out.println("✅ App launched successfully with session ID: " + sessionId);

            // Wait for 3 seconds to see the app
            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("❌ Test failed: " + e.getMessage());
            Assert.fail("App launch test failed: " + e.getMessage());
        }
    }

    @Test(priority = 2, description = "Verify app package name")
    public void testAppPackageName() {
        try {
            String currentPackage = DriverManager.getDriver().getCurrentPackage();
            Assert.assertEquals(currentPackage, "com.streefi.vendor",
                    "App package should match expected package");

            System.out.println("✅ App package verified: " + currentPackage);

        } catch (Exception e) {
            System.out.println("❌ Package verification failed: " + e.getMessage());
            Assert.fail("Package verification failed: " + e.getMessage());
        }
    }
}