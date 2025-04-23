package com.example.utils;

import com.microsoft.playwright.*;

/**
 * Utility class for managing Playwright instances.
 */
public class PlaywrightManager {
    
    private static Playwright playwright;
    
    /**
     * Get a Playwright Page instance for the specified browser.
     * 
     * @param browserType The browser to use (chromium, firefox, webkit)
     * @return A Playwright Page instance
     */
    public static Page getPage(String browserType) {
        // Initialize Playwright if not already initialized
        if (playwright == null) {
            playwright = Playwright.create();
        }
        
        Browser browser;
        
        // If browserType is null, default to chromium
        if (browserType == null || browserType.isEmpty()) {
            browserType = "chromium";
        }
        
        // Create browser based on browserType parameter
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
        
        // Create a new browser context and page
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        
        return page;
    }
    
    /**
     * Close Playwright instance.
     */
    public static void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}