package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverManager;

import java.io.File;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onStart(org.testng.ITestContext context) {
        // Create reports directory
        File reportsDir = new File("test-output/reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }

        // Initialize ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system information
        extent.setSystemInfo("Platform", "Android");
        extent.setSystemInfo("Device", "5434f104");
        extent.setSystemInfo("App Package", "com.streefi.vendor");
    }

    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed: " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        test.get().fail("Test Failed: " + result.getThrowable().getMessage());

        // Take screenshot on failure (optional)
        try {
            if (DriverManager.getDriver() != null) {
                String screenshot = DriverManager.getDriver().getScreenshotAs(org.openqa.selenium.OutputType.BASE64);
                test.get().addScreenCaptureFromBase64String(screenshot, "Screenshot on Failure");
            }
        } catch (Exception e) {
            System.out.println("Could not capture screenshot: " + e.getMessage());
        }
    }

    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped: " + result.getThrowable().getMessage());
    }

    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
        System.out.println("📊 Test Report Generated: test-output/reports/ExtentReport.html");
    }
}