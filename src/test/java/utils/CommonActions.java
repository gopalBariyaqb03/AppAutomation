package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;
import java.util.List;

public class CommonActions {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public CommonActions(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ThreadLocal counter so each test thread maintains its own step count
    private static ThreadLocal<Integer> stepCounter = ThreadLocal.withInitial(() -> 1);

    public void logPrint(String message) {
        if (message.toLowerCase().startsWith("step")) {
            int step = stepCounter.get();

            // Split the message on "::"
            String[] msg = message.split("::");

            // If message has "::", print only the right-hand side
            String stepMessage = msg.length > 1 ? msg[1].trim() : message;

            // Print with numbering
            String formatted = step + ". " + stepMessage;

            // Log in TestNG report + console
            Reporter.log("<br>" + formatted, true);
            System.out.println(formatted);

            // Increment step count for next call
            stepCounter.set(step + 1);
        } else {
            // For normal messages
            Reporter.log("<br>Message: " + message, true);
            System.out.println("Message: " + message);
        }
    }

    // Reset step counter at the start of each test
    public static void resetStepCounter() {
        stepCounter.set(1);
    }

    // Basic Actions
    public void click(By locator) {
        try {
            waitForElement(locator);
            driver.findElement(locator).click();
            System.out.println("✅ Clicked on element: " + locator);
        } catch (Exception e) {
            System.out.println("❌ Failed to click on element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public void type(By locator, String text) {
        try {
            waitForElement(locator);
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
            System.out.println("✅ Typed text '" + text + "' in element: " + locator);
        } catch (Exception e) {
            System.out.println("❌ Failed to type in element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public String getText(By locator) {
        try {
            waitForElement(locator);
            String text = driver.findElement(locator).getText();
            System.out.println("✅ Got text '" + text + "' from element: " + locator);
            return text;
        } catch (Exception e) {
            System.out.println("❌ Failed to get text from element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    // Wait Methods
    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Element State Methods
    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementSelected(By locator) {
        try {
            return driver.findElement(locator).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    // Mobile Specific Actions
    public void scrollDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
        System.out.println("✅ Scrolled down");
    }

    public void scrollUp() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endY = (int) (size.height * 0.8);

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
        System.out.println("✅ Scrolled up");
    }

    public void swipeLeft() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.8);
        int endX = (int) (size.width * 0.2);
        int y = size.height / 2;

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, y))
                .release()
                .perform();
        System.out.println("✅ Swiped left");
    }

    public void swipeRight() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.2);
        int endX = (int) (size.width * 0.8);
        int y = size.height / 2;

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, y))
                .release()
                .perform();
        System.out.println("✅ Swiped right");
    }

    public void tap(int x, int y) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(x, y)).perform();
        System.out.println("✅ Tapped at coordinates: (" + x + ", " + y + ")");
    }

    public void longPress(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            TouchAction touchAction = new TouchAction(driver);
            touchAction.longPress(PointOption.point(element.getLocation().x, element.getLocation().y))
                    .release()
                    .perform();
            System.out.println("✅ Long pressed on element: " + locator);
        } catch (Exception e) {
            System.out.println("❌ Failed to long press: " + e.getMessage());
            throw e;
        }
    }

    // Advanced Methods
    public void scrollToElement(By locator) {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                            + "new UiSelector().description(\"" + locator + "\"))"));
            System.out.println("✅ Scrolled to element: " + locator);
        } catch (Exception e) {
            System.out.println("❌ Failed to scroll to element: " + locator + " - " + e.getMessage());
        }
    }

    public void hideKeyboard() {
        try {
            if (driver.isKeyboardShown()) {
                driver.hideKeyboard();
                System.out.println("✅ Keyboard hidden");
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to hide keyboard: " + e.getMessage());
        }
    }

    public void goBack() {
        try {
            driver.navigate().back();
            System.out.println("✅ Navigated back");
        } catch (Exception e) {
            System.out.println("❌ Failed to navigate back: " + e.getMessage());
        }
    }

    // List and Multiple Elements
    public List<WebElement> getElements(By locator) {
        try {
            waitForElement(locator);
            return driver.findElements(locator);
        } catch (Exception e) {
            System.out.println("❌ Failed to get elements: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public int getElementCount(By locator) {
        return getElements(locator).size();
    }

    // App State Methods
    public void bringAppToForeground() {
        driver.activateApp("com.streefi.vendor");
    }

    public void sendAppToBackground(Duration duration) {
        driver.runAppInBackground(duration);
    }

    // Wait with custom timeout
    public void waitForElementWithTimeout(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}