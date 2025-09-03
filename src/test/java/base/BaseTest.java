package base;

import io.appium.java_client.android.AndroidDriver;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverManager;
import utils.ConfigReader;

import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    @BeforeMethod
    public void setUp() {

        try {
            // Read values from config.properties
            String appiumServerUrl = ConfigReader.getAppiumServerUrl();
            String deviceName = ConfigReader.getDeviceName();
            String platformVersion = ConfigReader.getPlatformVersion();
            String automationName = ConfigReader.getProperty("automation.name");
            String appPackage = ConfigReader.getAppPackage();
            String appActivity = ConfigReader.getAppActivity();
            String appPath = ConfigReader.getAppPath();
            int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicit.wait"));

            // Set desired capabilities
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", deviceName);
            caps.setCapability("appium:deviceName", deviceName);
            caps.setCapability("appium:platformVersion", platformVersion);
            caps.setCapability("appium:automationName", automationName);
            caps.setCapability("appium:appPackage", appPackage);
            caps.setCapability("appium:appActivity", appActivity);
            caps.setCapability("appium:app", appPath);
            caps.setCapability("appium:noReset", true);

            // Initialize driver using DriverManager
            AndroidDriver driver = new AndroidDriver(new URL(appiumServerUrl), caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Set driver in DriverManager
            DriverManager.setDriver(driver);

            System.out.println("App launched successfully!");

        } catch (Exception e) {
            System.out.println("Failed to launch app: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (DriverManager.getDriver() != null) {
                DriverManager.getDriver().quit();
                System.out.println("App closed successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error closing app: " + e.getMessage());
        }
    }
}