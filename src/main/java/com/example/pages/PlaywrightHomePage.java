package com.example.pages;

import com.microsoft.playwright.*;

/**
 * Page object for the Chrono24 homepage using Playwright.
 */
public class PlaywrightHomePage extends PlaywrightBasePage {
    
    // Base URL for the Chrono24 website
    private static final String BASE_URL = "https://www.chrono24.com/";
    
    // CSS selectors for elements on the homepage
    private static final String SEARCH_FIELD_SELECTOR = "input.search-field";
    private static final String SEARCH_BUTTON_SELECTOR = "button.search-button";
    private static final String ACCEPT_COOKIES_BUTTON_SELECTOR = "button.accept-cookies";
    
    /**
     * Constructor for PlaywrightHomePage.
     * 
     * @param page The Playwright Page instance
     */
    public PlaywrightHomePage(Page page) {
        super(page);
    }
    
    /**
     * Navigate to the homepage.
     */
    public void navigateTo() {
        page.navigate(BASE_URL);
        waitForPageToLoad();
    }
    
    /**
     * Accept cookies if the cookie banner is present.
     */
    public void acceptCookiesIfPresent() {
        try {
            if (isElementDisplayed(ACCEPT_COOKIES_BUTTON_SELECTOR)) {
                click(ACCEPT_COOKIES_BUTTON_SELECTOR);
            }
        } catch (Exception e) {
            // Cookie banner might not be present, so ignore any exceptions
        }
    }
    
    /**
     * Search for a watch.
     * 
     * @param searchTerm The search term
     * @return The PlaywrightSearchResultsPage
     */
    public PlaywrightSearchResultsPage searchForWatch(String searchTerm) {
        type(SEARCH_FIELD_SELECTOR, searchTerm);
        click(SEARCH_BUTTON_SELECTOR);
        return new PlaywrightSearchResultsPage(page);
    }
    
    /**
     * Check if the homepage is loaded.
     * 
     * @return true if the homepage is loaded, false otherwise
     */
    public boolean isLoaded() {
        return isElementDisplayed(SEARCH_FIELD_SELECTOR) && isElementDisplayed(SEARCH_BUTTON_SELECTOR);
    }
}