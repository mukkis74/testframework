package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the Chrono24 homepage.
 */
public class HomePage extends BasePage {
    
    // Base URL for the Chrono24 website
    private static final String BASE_URL = "https://www.chrono24.com/";
    
    // Web elements on the homepage
    @FindBy(css = "input.search-field")
    private WebElement searchField;
    
    @FindBy(css = "button.search-button")
    private WebElement searchButton;
    
    @FindBy(css = "button.accept-cookies")
    private WebElement acceptCookiesButton;
    
    /**
     * Constructor for HomePage.
     * 
     * @param driver The WebDriver instance
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to the homepage.
     */
    public void navigateTo() {
        driver.get(BASE_URL);
        waitForPageToLoad();
    }
    
    /**
     * Accept cookies if the cookie banner is present.
     */
    public void acceptCookiesIfPresent() {
        try {
            if (isElementDisplayed(acceptCookiesButton)) {
                click(acceptCookiesButton);
            }
        } catch (Exception e) {
            // Cookie banner might not be present, so ignore any exceptions
        }
    }
    
    /**
     * Search for a watch.
     * 
     * @param searchTerm The search term
     * @return The SearchResultsPage
     */
    public SearchResultsPage searchForWatch(String searchTerm) {
        type(searchField, searchTerm);
        click(searchButton);
        return new SearchResultsPage(driver);
    }
    
    /**
     * Check if the homepage is loaded.
     * 
     * @return true if the homepage is loaded, false otherwise
     */
    public boolean isLoaded() {
        return isElementDisplayed(searchField) && isElementDisplayed(searchButton);
    }
}