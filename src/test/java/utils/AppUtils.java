package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AppUtils {

    public static void waitForAppToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static WebDriverWait getExplicitWait(int timeoutInSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
    }

    public static void takeScreenshot(String testName) {
        try {
            AndroidDriver driver = DriverManager.getDriver();
            if (driver != null) {
                byte[] screenshot = driver.getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                // You can save screenshot to file here if needed
                System.out.println("Screenshot captured for test: " + testName);
            }
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    public static void restartApp() {
        try {
            AndroidDriver driver = DriverManager.getDriver();
            if (driver != null) {
                driver.terminateApp("com.streefi.vendor");
                Thread.sleep(2000);
                driver.activateApp("com.streefi.vendor");
                waitForAppToLoad();
            }
        } catch (Exception e) {
            System.out.println("Failed to restart app: " + e.getMessage());
        }
    }

    public static boolean isAppInstalled() {
        try {
            return DriverManager.getDriver().isAppInstalled("com.streefi.vendor");
        } catch (Exception e) {
            System.out.println("Error checking app installation: " + e.getMessage());
            return false;
        }
    }
}