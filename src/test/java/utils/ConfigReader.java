package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "C:\\Main_Folder\\AppLaunch\\src\\test\\resources\\config.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            System.out.println("Error loading config file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getAppiumServerUrl() {
        return getProperty("appium.server.url");
    }

    public static String getDeviceName() {
        return getProperty("device.name");
    }

    public static String getPlatformVersion() {
        return getProperty("platform.version");
    }

    public static String getAppPackage() {
        return getProperty("app.package");
    }

    public static String getAppActivity() {
        return getProperty("app.activity");
    }

    public static String getAppPath() {
        return getProperty("app.path");
    }
}