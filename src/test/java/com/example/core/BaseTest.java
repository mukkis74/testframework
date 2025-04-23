package com.example.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.example.utils.DriverManager;

/**
 * Base class for all test classes.
 * Provides common functionality like driver initialization and cleanup.
 */
public class BaseTest {

    protected WebDriver driver;

    /**
     * Set up method that runs before each test method.
     * Initializes the WebDriver instance.
     * 
     * @param browser The browser to use for the test (optional)
     */
    @BeforeMethod
    @Parameters(value = {"browser"})
    public void setUp(@org.testng.annotations.Optional("chrome") String browser) {
        // Initialize the WebDriver
        driver = DriverManager.getDriver(browser);
    }

    /**
     * Tear down method that runs after each test method.
     * Quits the WebDriver instance.
     */
    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
