package com.example.tests.e2e;

import com.example.core.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.SearchResultsPage;
import com.example.utils.ExtentReportManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * End-to-End tests for browsing and searching on the Chrono24 website.
 * These tests simulate a complete user journey from the homepage to viewing product details.
 */
@Epic("End-to-End Tests")
@Feature("Browse and Search")
public class BrowseAndSearchE2ETest extends BaseTest {
    
    /**
     * Test the complete user journey of searching for a watch and viewing its details.
     */
    @Test(description = "Complete user journey from search to product details")
    @Story("Watch Search and Details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the complete user journey of searching for a watch and viewing its details")
    public void testCompleteUserJourney() {
        ExtentReportManager.logInfo("Starting complete user journey test");
        
        // Step 1: Navigate to the homepage
        ExtentReportManager.logInfo("Step 1: Navigate to the homepage");
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        
        // Accept cookies if present
        homePage.acceptCookiesIfPresent();
        
        // Verify that the homepage is loaded
        Assert.assertTrue(homePage.isLoaded(), "Homepage should be loaded correctly");
        
        // Step 2: Search for a specific watch
        String searchTerm = "Rolex Submariner";
        ExtentReportManager.logInfo("Step 2: Search for " + searchTerm);
        SearchResultsPage searchResultsPage = homePage.searchForWatch(searchTerm);
        
        // Wait for search results to load
        searchResultsPage.waitForResultsToLoad();
        
        // Verify that search results are displayed
        Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                "Search results should be displayed for term: " + searchTerm);
        
        // Step 3: Click on the first search result
        ExtentReportManager.logInfo("Step 3: Click on the first search result");
        searchResultsPage.clickWatchItem(0);
        
        // Wait for the product detail page to load
        waitForPageLoad();
        
        // Step 4: Navigate back to search results
        ExtentReportManager.logInfo("Step 4: Navigate back to search results");
        driver.navigate().back();
        
        // Wait for search results to load again
        searchResultsPage.waitForResultsToLoad();
        
        // Step 5: Go to the next page of results
        ExtentReportManager.logInfo("Step 5: Go to the next page of results");
        searchResultsPage.goToNextPage();
        
        // Wait for the next page to load
        searchResultsPage.waitForResultsToLoad();
        
        // Verify that search results are still displayed
        Assert.assertTrue(searchResultsPage.areResultsDisplayed(), 
                "Search results should be displayed on the next page");
        
        ExtentReportManager.logPass("Complete user journey test passed successfully");
    }
    
    /**
     * Helper method to wait for page to load completely.
     */
    private void waitForPageLoad() {
        try {
            Thread.sleep(2000); // Basic wait for page load
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}