package ru.geekbrains.senchenko;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.Properties;

public class DriverInitializer {

    private static Properties properties = null;

    static {
        try {
            properties = new Properties();
            properties.load(DriverInitializer.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getWebDriver() {
        switch (getProperty("browser")) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            case "opera" -> {
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
        }

    }

    public static String getProperty(String key) {
        return properties == null ? null : properties.getProperty(key, "");
    }
}
