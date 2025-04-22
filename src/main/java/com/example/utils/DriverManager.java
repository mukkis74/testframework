package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Utility class for managing WebDriver instances.
 */
public class DriverManager {
    
    /**
     * Get a WebDriver instance for the specified browser.
     * 
     * @param browser The browser to use (chrome, firefox, edge, safari)
     * @return A WebDriver instance
     */
    public static WebDriver getDriver(String browser) {
        WebDriver driver;
        
        // If browser is null, default to chrome
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }
        
        // Create driver based on browser parameter
        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // Add any Firefox-specific options here
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                // Add any Edge-specific options here
                driver = new EdgeDriver(edgeOptions);
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                // Add any Chrome-specific options here
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        
        // Maximize window and set implicit wait
        driver.manage().window().maximize();
        
        return driver;
    }
}