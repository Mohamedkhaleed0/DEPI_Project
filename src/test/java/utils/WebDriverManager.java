package utils;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {

    private static WebDriver driver;

    public static void quitDriver() {
        if (driver != null) {
            driver.quit(); // Close the browser
            driver = null; // Reset the static variable
        }
    }
}
