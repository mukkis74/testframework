package com.example.core;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.example.utils.PlaywrightManager;

/**
 * Base class for all Playwright-based test classes.
 * Provides common functionality like page initialization and cleanup.
 */
public class PlaywrightBaseTest {

    protected Page page;
    protected Browser browser;
    protected BrowserContext context;
    protected Playwright playwright;

    /**
     * Set up method that runs before each test method.
     * Initializes the Playwright Page instance.
     * 
     * @param browserType The browser to use for the test (optional)
     */
    @BeforeMethod
    @Parameters(value = {"browser"})
    public void setUp(@org.testng.annotations.Optional("chromium") String browserType) {
        // Initialize Playwright
        playwright = Playwright.create();
        
        // Launch browser
        switch (browserType.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
        }
        
        // Create context and page
        context = browser.newContext();
        page = context.newPage();
    }

    /**
     * Tear down method that runs after each test method.
     * Closes the Playwright Page, Context, Browser, and Playwright instances.
     */
    @AfterMethod
    public void tearDown() {
        // Close page, context, browser, and playwright
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}